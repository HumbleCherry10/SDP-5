package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.RoutePlan;

/**
 * Concrete strategy implementing public transit routing (bus, metro/subway).
 * Combines fixed transit schedules, transfer points, and standard transit fares.
 * Demonstrates the Open/Closed Principle: added seamlessly without modifying existing classes.
 */
public class PublicTransitRouteStrategy implements RouteStrategy {

    private static final double STANDARD_TRANSIT_FARE = 1.75;
    private static final double EFFECTIVE_TRANSIT_SPEED_KMH = 32.0;

    @Override
    public RoutePlan buildRoute(String origin, String destination) {
        double transitDistance = calculateTransitDistance(origin, destination, 15.0);
        int durationMinutes = (int) Math.round((transitDistance / EFFECTIVE_TRANSIT_SPEED_KMH) * 60) + 12; // Includes transfer/wait
        double estimatedCost = STANDARD_TRANSIT_FARE;

        String directions = "Board Metro Red Line at Station A -> Transfer to Bus #42 at City Plaza -> Alight at " + destination;

        return new RoutePlan(
            origin,
            destination,
            getModeName(),
            transitDistance,
            durationMinutes,
            estimatedCost,
            directions
        );
    }

    @Override
    public String getModeName() {
        return "Public Transit (Metro / Bus)";
    }

    private double calculateTransitDistance(String from, String to, double baseline) {
        int seed = Math.abs((from + to).hashCode()) % 12;
        return baseline + seed * 0.6;
    }
}
