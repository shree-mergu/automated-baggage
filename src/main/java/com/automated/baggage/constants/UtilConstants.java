package com.automated.baggage.constants;

import java.util.HashMap;
import java.util.Map;

public class UtilConstants {

    public static final Map<String, String> INPUT_TYPES = new HashMap<>();
    static {
        INPUT_TYPES.put("# Section: Conveyor System", "Conveyor");
        INPUT_TYPES.put("# Section: Departures", "Departures");
        INPUT_TYPES.put("# Section: Bags", "Bags");
    }

    public static final String CONVEYOR = "Conveyor";
    public static final String DEPARTURES = "Departures";
    public static final String BAGS = "Bags";

    public static final String SPACE = "\\s+";

    public static final String BAGGAGE_CLAIM = "BaggageClaim";

    public static final int MAX_WEIGHT = 9999999;
}
