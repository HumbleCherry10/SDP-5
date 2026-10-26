package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.RoutePlan;

/**
 * Concrete strategy implementing pedestrian route calculation.
 * Avoids freeways and busy highways, favors parks, sidewalks, and pedestrian zones.
 * Incurs zero travel fee.
 */
public class WalkingRouteStrategy implements RouteStrategy {

    private static final double WALKING_SPEED_KMH = 4.8;

    @Override
    public RoutePlan buildRoute(String origin, String destination) {
        // Pedestrian paths take shortcuts through parks and alleys, reducing raw distance
        double baseDistance = calculatePedestrianDistance(origin, destination, 6.2);
        int durationMinutes = (int) Math.round((baseDistance / WALKING_SPEED_KMH) * 60);
        double estimatedCost = 0.0; // Walking is free

        String directions = "Cross pedestrian walkway -> Walk through Central Green Park -> Follow pedestrian avenue to " + destination;

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
        return "Walking / Pedestrian";
    }

    private double calculatePedestrianDistance(String from, String to, double baseline) {
        int seed = Math.abs((from + to).hashCode()) % 8;
        return baseline + seed * 0.3;
    }
}
