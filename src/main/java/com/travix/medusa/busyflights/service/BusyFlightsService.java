package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import javax.validation.Valid;
import java.util.List;

public interface BusyFlightsService {

    List<BusyFlightsResponse> searchFlights(@Valid BusyFlightsRequest busyFlightsRequest);
}
