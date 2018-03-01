package com.automated.baggage.service.model;

import java.io.Serializable;

public class BaggagePath implements Serializable, Comparable<BaggagePath> {

    private String path;
    private int travelTime;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    @Override
    public int compareTo(BaggagePath baggagePath) {

        if (this.travelTime > baggagePath.getTravelTime()) {
            return 1;
        } else if (this.travelTime < baggagePath.getTravelTime()) {
            return -1;
        }
        return 0;
    }
}
