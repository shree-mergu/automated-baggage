package com.automated.baggage.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Gate implements Serializable {

    private String name;
    private List<Route> routes;
    private boolean visited;

    public Gate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Route> getRoutes() {
        return routes == null ? new ArrayList<>() : routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
