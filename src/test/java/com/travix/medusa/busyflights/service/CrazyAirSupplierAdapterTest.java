package com.travix.medusa.busyflights.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.supplier.CrazyAirHttpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrazyAirSupplierAdapterTest {

    @MockBean
    private CrazyAirHttpService crazyAirHttpService;

    @Autowired
    private CrazyAirSupplierAdapter crazyAirSupplierAdapter;

    private BusyFlightsRequest req;

    @Before
    public void init() {
        req = new BusyFlightsRequest();
        req.setReturnDate("2018-02-04");
        req.setOrigin("ABC");
        req.setDestination("XYZ");
        req.setDepartureDate("2018-01-24");
        req.setNumberOfPassengers(3);
    }

    @Test
    public void testNullResult() {
        when(crazyAirHttpService.searchFlights(any())).thenReturn(null);
        List<BusyFlightsResponse> resp = crazyAirSupplierAdapter.searchFlights(req);
        assertNotNull(resp);
        assertEquals(0, resp.size());
    }

    @Test
    public void test() {
        CrazyAirRequest r = new CrazyAirRequest();
        r.setPassengerCount(req.getNumberOfPassengers());
        r.setOrigin(req.getOrigin());
        r.setDestination(req.getDestination());
        r.setDepartureDate(req.getDepartureDate());
        r.setReturnDate(req.getReturnDate());

        List<CrazyAirResponse> list = SampleCreationUtil.createCrazyAirResponses(r, 5);

        when(crazyAirHttpService.searchFlights(any())).thenReturn(list);
        List<BusyFlightsResponse> responses = crazyAirSupplierAdapter.searchFlights(req);
        assertNotNull(responses);
        assertEquals(responses.size(), list.size());
        for(int i = 0; i < responses.size(); i++) {
            BusyFlightsResponse resp = responses.get(i);
            CrazyAirResponse cr = list.get(i);
            assertNotNull(resp);
            assertEquals(resp.getSupplier(), crazyAirSupplierAdapter.getSupplierName());
            assertEquals(resp.getDepartureAirportCode(), cr.getDepartureAirportCode());
            assertEquals(resp.getArrivalDate(), cr.getArrivalDate());
            assertEquals(resp.getDepartureDate(), cr.getDepartureDate());
            assertEquals(resp.getDestinationAirportCode(), cr.getDestinationAirportCode());
        }

    }
}
