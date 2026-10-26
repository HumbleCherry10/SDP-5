package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.RoutePlan;

/**
 * Concrete strategy implementing bicycle route calculation.
 * Prioritizes dedicated bike lanes, shared trails, and paths with low elevation gradient.
 */
public class CyclingRouteStrategy implements RouteStrategy {

    private static final double CYCLING_SPEED_KMH = 17.5;

    @Override
    public RoutePlan buildRoute(String origin, String destination) {
        double baseDistance = calculateBicycleDistance(origin, destination, 9.8);
        int durationMinutes = (int) Math.round((baseDistance / CYCLING_SPEED_KMH) * 60);
        double estimatedCost = 0.0; // Bicycle ownership has 0 direct ride cost

        String directions = "Join South Riverside Bike Path -> Follow designated cycleway along Boulevard -> Arrive at bike rack near " + destination;

        return new RoutePlan(
            origin,
            destination,
            getModeName(),
            baseDistance,
            durationMinutes,
            estimatedCost,
            directions
        );
    }

    @Override
    public String getModeName() {
        return "Bicycle / Cycling";
    }

    private double calculateBicycleDistance(String from, String to, double baseline) {
        int seed = Math.abs((from + to).hashCode()) % 10;
        return baseline + seed * 0.4;
    }
}
