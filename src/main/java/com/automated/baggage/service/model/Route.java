package com.automated.baggage.service.model;

import java.io.Serializable;

public class Route implements Serializable {

    private int travelTime;
    private Gate destination;

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public Gate getDestination() {
        return destination;
    }

    public void setDestination(Gate destination) {
        this.destination = destination;
    }
}
