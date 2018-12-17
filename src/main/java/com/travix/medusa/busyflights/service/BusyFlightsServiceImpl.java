package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Validated
public class BusyFlightsServiceImpl implements BusyFlightsService {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private List<FlightSearchSupplier> flightSearchSuppliers;

    @Override
    public List<BusyFlightsResponse> searchFlights(@Valid BusyFlightsRequest busyFlightsRequest) {

        try {
            if (simpleDateFormat.parse(busyFlightsRequest.getDepartureDate()).after(simpleDateFormat.parse(busyFlightsRequest.getReturnDate()))) {
                Set<ConstraintViolation<?>> set = new HashSet<>(1);
                set.add(ConstraintViolationImpl.forBeanValidation("'returnDate' can not be before 'departureDate'", null, "'returnDate' can not be before 'departureDate'", null, null, null, null, null, null, null, null));
                throw new ConstraintViolationException(set);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        List<BusyFlightsResponse> result = new ArrayList<>();
        for(FlightSearchSupplier supplier : flightSearchSuppliers) {
            List<BusyFlightsResponse> response = supplier.searchFlights(busyFlightsRequest);
            response.forEach(r -> {
                r.setSupplier(supplier.getSupplierName());
                r.setFare(Math.round(r.getFare() * 100d)/100d);
            });
            result.addAll(response);
        }
        result.sort(Comparator.comparingDouble(BusyFlightsResponse::getFare));
        return result;
    }
}
