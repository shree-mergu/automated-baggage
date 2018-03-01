package com.automated.baggage.service;

import com.automated.baggage.service.model.AutomatedBaggageRequest;

import java.util.List;

public interface AutomatedBaggageService {

    /**
     * ConveyorSystem, Departures, Bags
     *
     * @return
     */
    List<String> getOptimizedBaggageRoutes(AutomatedBaggageRequest automatedBaggageRequest);
}
