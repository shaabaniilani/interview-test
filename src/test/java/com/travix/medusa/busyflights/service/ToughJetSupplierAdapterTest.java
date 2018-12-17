package com.travix.medusa.busyflights.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.supplier.ToughJetHttpService;
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
    public class ToughJetSupplierAdapterTest {

        @MockBean
        private ToughJetHttpService toughJetHttpService;

        @Autowired
        private ToughJetSupplierAdapter toughJetSupplierAdapter;

        private BusyFlightsRequest req;

        @Before
        public void init() {
            req = SampleCreationUtil.createBusyFlightsRequest();
        }

        @Test
        public void testNullResult() {
            when(toughJetHttpService.searchFlights(any())).thenReturn(null);
            List<BusyFlightsResponse> resp = toughJetSupplierAdapter.searchFlights(req);
            assertNotNull(resp);
            assertEquals(0, resp.size());
        }

        @Test
        public void test() {
            ToughJetRequest r = new ToughJetRequest();
            r.setTo(req.getDestination());
            r.setOutboundDate(req.getDepartureDate());
            r.setNumberOfAdults(req.getNumberOfPassengers());
            r.setInboundDate(req.getReturnDate());
            r.setFrom(req.getOrigin());

            List<ToughJetResponse> list = SampleCreationUtil.createToughJetResponses(r, 5);

            when(toughJetHttpService.searchFlights(any())).thenReturn(list);
            List<BusyFlightsResponse> responses = toughJetSupplierAdapter.searchFlights(req);
            assertNotNull(responses);
            assertEquals(responses.size(), list.size());
            for(int i = 0; i < responses.size(); i++) {
                BusyFlightsResponse resp = responses.get(i);
                ToughJetResponse cr = list.get(i);
                assertNotNull(resp);
                assertEquals(resp.getSupplier(), toughJetSupplierAdapter.getSupplierName());
                assertEquals(resp.getDepartureAirportCode(), cr.getDepartureAirportName());
                assertEquals(resp.getArrivalDate(), cr.getInboundDateTime());
                assertEquals(resp.getDepartureDate(), cr.getOutboundDateTime());
                assertEquals(resp.getDestinationAirportCode(), cr.getArrivalAirportName());
            }

        }
    }

