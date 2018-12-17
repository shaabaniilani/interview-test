package com.travix.medusa.busyflights.domain.busyflights;

import com.travix.medusa.busyflights.validator.DateFormat;

import javax.validation.constraints.*;

public class BusyFlightsRequest {

    @NotNull
    @Size(min = 3, max = 3, message = "Invalid 'origin' length.")
    private String origin;
    @NotNull
    @Size(min = 3, max = 3, message = "Invalid 'destination' length.")
    private String destination;
    @NotNull
    @DateFormat(message = "Invalid 'departureDate' format.")
    private String departureDate;
    @NotNull
    @DateFormat(message = "Invalid 'returnDate' format.")
    private String returnDate;
    @NotNull
    @Min(value = 1, message = "'numberOfPassengers' Can not be less that 1.")
    @Max(value = 4, message = "'numberOfPassengers' Can not be more than 4.")
    private int numberOfPassengers;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(final int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
