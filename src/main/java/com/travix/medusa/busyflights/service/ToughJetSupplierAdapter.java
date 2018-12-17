package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.supplier.ToughJetHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToughJetSupplierAdapter implements FlightSearchSupplier {

    private static final String SUPPLIER_NAME = "ToughJet";

    @Autowired
    private ToughJetHttpService toughJetHttpService;

    @Override
    public String getSupplierName() {
        return SUPPLIER_NAME;
    }

    @Override
    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest) {
        List<ToughJetResponse> responses = toughJetHttpService.searchFlights(convertRequest(busyFlightsRequest));
        if(responses == null)
            return Collections.emptyList();
        return responses.stream().map(this::convertResponse).collect(Collectors.toList());
    }

    private ToughJetRequest convertRequest(BusyFlightsRequest req) {
        ToughJetRequest request = new ToughJetRequest();
        request.setFrom(req.getOrigin());
        request.setInboundDate(req.getReturnDate());
        request.setNumberOfAdults(req.getNumberOfPassengers());
        request.setOutboundDate(req.getDepartureDate());
        request.setTo(req.getDestination());
        return request;
    }

    private BusyFlightsResponse convertResponse(ToughJetResponse resp) {
        BusyFlightsResponse response = new BusyFlightsResponse();
        response.setSupplier(SUPPLIER_NAME);
        response.setAirline(resp.getCarrier());
        response.setArrivalDate(resp.getInboundDateTime());
        response.setDepartureAirportCode(resp.getDepartureAirportName());
        response.setDepartureDate(resp.getOutboundDateTime());
        response.setDestinationAirportCode(resp.getArrivalAirportName());
        response.setFare((resp.getBasePrice() - resp.getDiscount()) * (1 + resp.getTax() / 100d));
        return response;
    }
}
