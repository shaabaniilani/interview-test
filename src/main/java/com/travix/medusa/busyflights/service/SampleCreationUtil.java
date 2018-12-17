package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SampleCreationUtil {

    public static List<CrazyAirResponse> createCrazyAirResponses(CrazyAirRequest req, int n) {
        List<CrazyAirResponse> responses = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            CrazyAirResponse resp = new CrazyAirResponse();
            resp.setDestinationAirportCode(req.getDestination());
            resp.setDepartureAirportCode(req.getOrigin());
            resp.setPrice(ThreadLocalRandom.current().nextDouble(50, 500));
            resp.setAirline("Airline " + ThreadLocalRandom.current().nextInt(1, 400));
            int hour = ThreadLocalRandom.current().nextInt(0, 9);
            resp.setDepartureDate(req.getDepartureDate() + "T0" + hour + ":" + ThreadLocalRandom.current().nextInt(10, 59) + ":" + ThreadLocalRandom.current().nextInt(10, 59));
            resp.setArrivalDate(req.getReturnDate() + "T" + (hour + 10) + ":" + ThreadLocalRandom.current().nextInt(10, 59) + ":" + ThreadLocalRandom.current().nextInt(10, 59));
            resp.setCabinclass(ThreadLocalRandom.current().nextBoolean() ? "B" : "E");
            responses.add(resp);
        }
        return responses;
    }

    public static List<ToughJetResponse> createToughJetResponses(ToughJetRequest req, int n) {

        List<ToughJetResponse> responses = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            ToughJetResponse resp = new ToughJetResponse();
            resp.setArrivalAirportName(req.getTo());
            resp.setDepartureAirportName(req.getFrom());
            resp.setBasePrice(ThreadLocalRandom.current().nextDouble(50, 500));
            resp.setCarrier("Airline " + ThreadLocalRandom.current().nextInt(1, 400));
            resp.setDiscount(ThreadLocalRandom.current().nextDouble(0, 20));
            int hour = ThreadLocalRandom.current().nextInt(0, 9);
            resp.setInboundDateTime(req.getInboundDate() + "T0" + hour + ":" + ThreadLocalRandom.current().nextInt(10, 59) + ":" + ThreadLocalRandom.current().nextInt(10, 59));
            resp.setOutboundDateTime(req.getOutboundDate() + "T" + (hour + 10) + ":" + ThreadLocalRandom.current().nextInt(10, 59) + ":" + ThreadLocalRandom.current().nextInt(10, 59));
            resp.setTax(12);
            responses.add(resp);
        }
        return responses;
    }

    public static List<BusyFlightsResponse> createBusyFlightsResponses(BusyFlightsRequest req, int n) {
        List<BusyFlightsResponse> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            BusyFlightsResponse resp = new BusyFlightsResponse();
            resp.setDestinationAirportCode(req.getDestination());
            resp.setDepartureAirportCode(req.getOrigin());
            resp.setFare(ThreadLocalRandom.current().nextDouble(50, 500));
            resp.setAirline("Airline " + ThreadLocalRandom.current().nextInt(1, 400));
            int hour = ThreadLocalRandom.current().nextInt(0, 9);
            resp.setDepartureDate(req.getDepartureDate() + "T0" + hour + ":" + ThreadLocalRandom.current().nextInt(10, 59) + ":" + ThreadLocalRandom.current().nextInt(10, 59));
            resp.setArrivalDate(req.getReturnDate() + "T" + (hour + 10) + ":" + ThreadLocalRandom.current().nextInt(10, 59) + ":" + ThreadLocalRandom.current().nextInt(10, 59));
            list.add(resp);
        }
        return list;
    }

    public static BusyFlightsRequest createBusyFlightsRequest() {
        BusyFlightsRequest req = new BusyFlightsRequest();
        req.setReturnDate("2018-02-04");
        req.setOrigin("ABC");
        req.setDestination("XYZ");
        req.setDepartureDate("2018-01-24");
        req.setNumberOfPassengers(3);

        return req;
    }
}
