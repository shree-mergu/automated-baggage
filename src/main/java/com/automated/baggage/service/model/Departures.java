package com.automated.baggage.service.model;

import java.io.Serializable;
import java.util.Map;

public class Departures implements Serializable {

    private Map<String, Flight> flights;

    public Map<String, Flight> getFlights() {
        return flights;
    }

    public void setFlights(Map<String, Flight> flights) {
        this.flights = flights;
    }
}
