package com.automated.baggage.service.model;

import com.automated.baggage.main.AutomatedBaggage;

import java.io.Serializable;
import java.util.List;

public class AutomatedBaggageRequest implements Serializable {

    private ConveyorSystem conveyorSystem;
    private Departures departures;
    private List<Bag> bags;

    public AutomatedBaggageRequest(ConveyorSystem conveyorSystem, Departures departures, List<Bag> bags) {
        this.bags = bags;
        this.conveyorSystem = conveyorSystem;
        this.departures = departures;
    }

    public ConveyorSystem getConveyorSystem() {
        return conveyorSystem;
    }

    public void setConveyorSystem(ConveyorSystem conveyorSystem) {
        this.conveyorSystem = conveyorSystem;
    }

    public Departures getDepartures() {
        return departures;
    }

    public void setDepartures(Departures departures) {
        this.departures = departures;
    }

    public List<Bag> getBags() {
        return bags;
    }

    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }
}
