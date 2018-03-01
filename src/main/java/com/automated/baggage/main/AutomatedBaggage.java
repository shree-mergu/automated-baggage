package com.automated.baggage.main;

import com.automated.baggage.service.AutomatedBaggageService;
import com.automated.baggage.service.impl.AutomatedBaggageServiceImpl;
import com.automated.baggage.service.model.AutomatedBaggageRequest;
import com.automated.baggage.service.util.AutomatedBaggageUtil;

public class AutomatedBaggage {

    public static void main(String... args) throws Exception {

        AutomatedBaggageUtil automatedBaggageUtil = new AutomatedBaggageUtil();
        AutomatedBaggageService automatedBaggageService = new AutomatedBaggageServiceImpl(automatedBaggageUtil);
        AutomatedBaggageRequest automatedBaggageRequest = automatedBaggageUtil.readInputFile("sample-input.txt");
        automatedBaggageService.getOptimizedBaggageRoutes(automatedBaggageRequest);
    }
}
