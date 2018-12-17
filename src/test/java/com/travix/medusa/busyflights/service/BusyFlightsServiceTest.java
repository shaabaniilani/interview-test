package com.travix.medusa.busyflights.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusyFlightsServiceTest {

    @MockBean
    private CrazyAirSupplierAdapter crazyAirSupplierAdapter;

    @MockBean
    private ToughJetSupplierAdapter toughJetSupplierAdapter;

    @Autowired
    private BusyFlightsService busyFlightsService;

    @Test
    public void testOK() {

        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();

        List<BusyFlightsResponse> crazyList = SampleCreationUtil.createBusyFlightsResponses(req, 3);
        List<BusyFlightsResponse> toughList = SampleCreationUtil.createBusyFlightsResponses(req, 4);

        when(crazyAirSupplierAdapter.searchFlights(any())).thenReturn(crazyList);
        when(toughJetSupplierAdapter.searchFlights(any())).thenReturn(toughList);



        List<BusyFlightsResponse> resp = busyFlightsService.searchFlights(req);
        assertNotNull(resp);
        assertEquals(resp.size(), crazyList.size() + toughList.size());
        for(int i = 1; i < resp.size(); i++) {
            assertTrue(resp.get(i).getFare() >= resp.get(i - 1).getFare());
        }
    }

    @Test
    public void testEmptyResult() {

        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();

        when(crazyAirSupplierAdapter.searchFlights(any())).thenReturn(Collections.emptyList());
        when(toughJetSupplierAdapter.searchFlights(any())).thenReturn(Collections.emptyList());

        List<BusyFlightsResponse> resp = busyFlightsService.searchFlights(req);
        assertNotNull(resp);
        assertEquals(resp.size(), 0);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationOriginNull() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setOrigin(null);
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationOriginLength1() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setOrigin("A");
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationOriginLength2() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setOrigin("AAAA");
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationDestinationNull() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setDestination(null);
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationDestinationLength1() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setDestination("A");
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationDestinationLength2() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setDestination("AAAA");
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationDepartureDateNull() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setDepartureDate(null);
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationDepartureDateFormat() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setDepartureDate("1232-98");
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationDepartureDateFormat2() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setDepartureDate("2018-03-49");
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationReturnDateNull() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setReturnDate(null);
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationReturnDateFormat() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setReturnDate("123-34");
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationReturnDateFormat2() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setReturnDate("2018-03-39");
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationNumberOfPassengersNull() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setNumberOfPassengers(0);
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationNumberOfPassengersLessThanOne() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setNumberOfPassengers(-1);
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationNumberOfPassengersGreaterThanFour() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setNumberOfPassengers(5);
        busyFlightsService.searchFlights(req);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testValidationDepartureDateBeforeReturnDate() {
        BusyFlightsRequest req = SampleCreationUtil.createBusyFlightsRequest();
        req.setDepartureDate("2018-10-12");
        req.setReturnDate("2018-10-11");
        busyFlightsService.searchFlights(req);
    }

}
