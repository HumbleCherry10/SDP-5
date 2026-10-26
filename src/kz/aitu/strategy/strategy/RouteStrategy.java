package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.RoutePlan;

/**
 * Common Strategy interface declaring the contract for all route-planning algorithms.
 */
public interface RouteStrategy {
    /**
     * Calculates and constructs a route between origin and destination.
     *
     * @param origin Starting location address or name
     * @param destination Target destination address or name
     * @return Generated RoutePlan containing travel metrics and instructions
     */
    RoutePlan buildRoute(String origin, String destination);

    /**
     * Returns an intention-revealing name for the transport strategy.
     *
     * @return Strategy transport mode name
     */
    String getModeName();
}
