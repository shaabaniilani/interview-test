package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.supplier.CrazyAirHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CrazyAirSupplierAdapter implements FlightSearchSupplier {

    private static final String SUPPLIER_NAME = "CrazyAir";

    @Autowired
    private CrazyAirHttpService crazyAirHttpService;

    @Override
    public String getSupplierName() {
        return SUPPLIER_NAME;
    }

    @Override
    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest) {
        List<CrazyAirResponse> responses = crazyAirHttpService.searchFlights(convertRequest(busyFlightsRequest));
        if(responses == null)
            return Collections.emptyList();
        return responses.stream().map(this::convertResponse).collect(Collectors.toList());
    }

    private CrazyAirRequest convertRequest(BusyFlightsRequest req) {
        CrazyAirRequest request = new CrazyAirRequest();
        request.setDepartureDate(req.getDepartureDate());
        request.setDestination(req.getDestination());
        request.setOrigin(req.getOrigin());
        request.setPassengerCount(req.getNumberOfPassengers());
        request.setReturnDate(req.getReturnDate());
        return request;
    }

    private BusyFlightsResponse convertResponse(CrazyAirResponse resp) {
        BusyFlightsResponse response = new BusyFlightsResponse();
        response.setSupplier(SUPPLIER_NAME);
        response.setAirline(resp.getAirline());
        response.setArrivalDate(resp.getArrivalDate());
        response.setDepartureAirportCode(resp.getDepartureAirportCode());
        response.setDepartureDate(resp.getDepartureDate());
        response.setDestinationAirportCode(resp.getDestinationAirportCode());
        response.setFare(resp.getPrice());
        return response;
    }
}
