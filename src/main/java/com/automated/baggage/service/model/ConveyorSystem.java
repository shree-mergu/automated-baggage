package com.automated.baggage.service.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ConveyorSystem implements Serializable {

    private Map<String, Gate> gates;

    public Map<String, Gate> getGates() {
        return gates == null ? new HashMap<>() : gates;
    }

    public void setGates(Map<String, Gate> gates) {
        this.gates = gates;
    }
}
