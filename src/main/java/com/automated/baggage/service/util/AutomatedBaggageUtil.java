package com.automated.baggage.service.util;

import com.automated.baggage.constants.UtilConstants;
import com.automated.baggage.service.model.*;

import java.io.File;
import java.util.*;

public class AutomatedBaggageUtil {

    public void createGate(String[] fields, Map<String, Gate> gates) {

        if (fields.length == 3) {
            String gateName = fields[0];
            Gate sourceGate = getGate(gates, gateName);

            String destinationGateName = fields[1];
            Gate destinationGate = getGate(gates, destinationGateName);

            int travelTime = Integer.parseInt(fields[2]);

            sourceGate = updateRoute(sourceGate, destinationGate, travelTime);
            destinationGate = updateRoute(destinationGate, sourceGate, travelTime);

            gates.put(gateName, sourceGate);
            gates.put(destinationGateName, destinationGate);

        }
    }

    public Flight createFlight(String[] fields) {

        if (fields.length == 4) {
            Flight flight = new Flight();
            flight.setName(fields[0]);
            flight.setGateName(fields[1]);
            flight.setDestination(fields[2]);
            flight.setTime(fields[3]);

            return flight;
        }

        return null;
    }

    public Bag createBag(String[] fields) {

        if (fields.length == 3) {
            Bag bag = new Bag();
            bag.setId(fields[0]);
            bag.setSource(fields[1]);
            bag.setDestination(fields[2]);

            return bag;
        }

        return null;
    }

    private Gate getGate(Map<String, Gate> gates, String gateName) {

        return gates.containsKey(gateName) ? gates.get(gateName) : new Gate(gateName);
    }

    private Gate updateRoute(Gate source, Gate destination, int travelTime) {

        Route source2Destination = new Route();
        source2Destination.setDestination(destination);
        source2Destination.setTravelTime(travelTime);
        List<Route> routes = source.getRoutes();
        routes.add(source2Destination);
        source.setRoutes(routes);

        return source;
    }

    public AutomatedBaggageRequest readInputFile(String fileName) throws Exception {

        Scanner input = new Scanner(new File(ClassLoader.getSystemResource("sample-input.txt").toURI()));
        String type = null;
        ConveyorSystem conveyorSystem = new ConveyorSystem();
        Departures departures = new Departures();
        List<Bag> bags = new ArrayList<>();

        while (input.hasNextLine()) {
            String line = input.nextLine();

            if (UtilConstants.INPUT_TYPES.containsKey(line)) {
                type = UtilConstants.INPUT_TYPES.get(line);
            } else if (UtilConstants.CONVEYOR.equals(type)) {
                Map<String, Gate> gates = conveyorSystem.getGates();
                this.createGate(line.split(UtilConstants.SPACE), gates);
                conveyorSystem.setGates(gates);
            } else if (UtilConstants.DEPARTURES.equals(type)) {
                Map<String, Flight> flights = departures.getFlights();
                if (flights == null) {
                    flights = new HashMap<>();
                }
                Flight flight = this.createFlight(line.split(UtilConstants.SPACE));
                flights.put(flight.getName(), flight);
                departures.setFlights(flights);
            } else if (UtilConstants.BAGS.equals(type)) {
                Bag bag = this.createBag(line.split(UtilConstants.SPACE));
                bags.add(bag);
            }
        }

        return new AutomatedBaggageRequest(conveyorSystem, departures, bags);
    }

    public void resetVisitedFlag(Map<String, Gate> gates) {
        for (Gate gate: gates.values()) {
            gate.setVisited(false);
        }
    }

}
