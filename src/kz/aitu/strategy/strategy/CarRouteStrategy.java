package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.RoutePlan;

/**
 * Concrete strategy implementing automotive route calculation.
 * Prioritizes highways, computes estimated fuel and toll costs,
 * and factors in road traffic.
 */
public class CarRouteStrategy implements RouteStrategy {

    private static final double AVERAGE_SPEED_KMH = 65.0;
    private static final double FUEL_RATE_PER_KM = 0.12; // $0.12 per km
    private static final double TOLL_ESTIMATE = 2.50;

    @Override
    public RoutePlan buildRoute(String origin, String destination) {
        // Deterministic mock calculation based on input string properties for realistic variation
        double baseDistance = calculateMockDistance(origin, destination, 24.5);
        int durationMinutes = (int) Math.round((baseDistance / AVERAGE_SPEED_KMH) * 60) + 8; // +8 min traffic buffer
        double estimatedCost = (baseDistance * FUEL_RATE_PER_KM) + TOLL_ESTIMATE;

        String directions = "Take Highway E123 -> Merge onto Central Ring Expressway -> Exit 14B toward " + destination;

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
        return "Car / Driving";
    }

    private double calculateMockDistance(String from, String to, double baseline) {
        int seed = Math.abs((from + to).hashCode()) % 15;
        return baseline + seed * 0.8;
    }
}
