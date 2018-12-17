package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusyFlightsController {

    @Autowired
    private BusyFlightsService busyFlightsService;


    @GetMapping("/flights")
    public List<BusyFlightsResponse> searchFlight(@RequestParam String origin,
                                                  @RequestParam String destination,
                                                  @RequestParam String departureDate,
                                                  @RequestParam String returnDate,
                                                  @RequestParam int numberOfPassengers) {
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setNumberOfPassengers(numberOfPassengers);
        busyFlightsRequest.setDepartureDate(departureDate);
        busyFlightsRequest.setDestination(destination);
        busyFlightsRequest.setOrigin(origin);
        busyFlightsRequest.setReturnDate(returnDate);
        return busyFlightsService.searchFlights(busyFlightsRequest);
    }
}
