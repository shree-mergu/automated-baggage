package com.automated.baggage.service.impl;

import com.automated.baggage.constants.UtilConstants;
import com.automated.baggage.service.AutomatedBaggageService;
import com.automated.baggage.service.model.AutomatedBaggageRequest;
import com.automated.baggage.service.model.BaggagePath;
import com.automated.baggage.service.model.Flight;
import com.automated.baggage.service.model.Gate;
import com.automated.baggage.service.util.AutomatedBaggageUtil;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AutomatedBaggageServiceImpl implements AutomatedBaggageService {

    private AutomatedBaggageUtil automatedBaggageUtil;

    public AutomatedBaggageServiceImpl(AutomatedBaggageUtil automatedBaggageUtil) {
        this.automatedBaggageUtil = automatedBaggageUtil;
    }

    @Override
    public List<String> getOptimizedBaggageRoutes(AutomatedBaggageRequest automatedBaggageRequest) {

        automatedBaggageRequest.getBags().forEach(bag -> {
            String sourceGateName = bag.getSource();
            String destinationName = bag.getDestination();

            Flight flight = automatedBaggageRequest.getDepartures().getFlights().get(destinationName);
            if (flight == null) {
                destinationName = UtilConstants.BAGGAGE_CLAIM;
            } else {
                destinationName = flight.getGateName();
            }

            Gate source = automatedBaggageRequest.getConveyorSystem().getGates().get(sourceGateName);
            Gate destination = automatedBaggageRequest.getConveyorSystem().getGates().get(destinationName);
            BaggagePath path = getOptimizedRoute(source, destination);

            if (path != null) {
                System.out.println(bag.getId()+" "+path.getPath() + " : " + path.getTravelTime());
            } else {
                System.out.println(" Path not found ");
            }

            automatedBaggageUtil.resetVisitedFlag(automatedBaggageRequest.getConveyorSystem().getGates());
        });

        return null;
    }

    public BaggagePath getOptimizedRoute(Gate source, Gate destination) {

        Set<BaggagePath> paths = new TreeSet<>();
        source.setVisited(true);

        //Iterate all the routes
        source.getRoutes().stream().filter(route -> (route.getDestination() != null && !route.getDestination().isVisited())).forEach(route -> {

            BaggagePath path = new BaggagePath();
            path.setPath(source.getName());
            route.getDestination().setVisited(true);

            if (route.getDestination().equals(destination)) {
                path.setPath(path.getPath() + " " + destination.getName());
                path.setTravelTime(route.getTravelTime());
            } else {
                BaggagePath childPath = getOptimizedRoute(route.getDestination(), destination);
                if (childPath != null) {
                    path.setPath(path.getPath() + " " + childPath.getPath());
                    path.setTravelTime(route.getTravelTime() + childPath.getTravelTime());
                } else {
                    path.setTravelTime(UtilConstants.MAX_WEIGHT);
                }
            }

            paths.add(path);
        });

        return !paths.isEmpty()? paths.iterator().next() : null;
    }
}
