package kz.aitu.strategy.context;

import kz.aitu.strategy.model.RoutePlan;
import kz.aitu.strategy.strategy.RouteStrategy;
import java.util.Objects;

/**
 * Context class in the Strategy design pattern.
 * Holds a reference to a RouteStrategy abstraction and delegates route
 * calculation directly to whatever strategy is currently configured.
 *
 * Notice:
 * 1. Navigator is completely agnostic to concrete algorithms (Car, Walk, Bicycle, Transit).
 * 2. It contains ZERO conditional logic (if / switch on transport types).
 * 3. The strategy reference can be swapped dynamically at runtime.
 */
public class Navigator {

    private RouteStrategy routeStrategy;

    /**
     * Initializes the Navigator with a default route strategy.
     *
     * @param initialStrategy the initial strategy to use, must not be null
     */
    public Navigator(RouteStrategy initialStrategy) {
        setStrategy(initialStrategy);
    }

    /**
     * Replaces the current routing strategy at runtime.
     *
     * @param routeStrategy new strategy implementation, must not be null
     */
    public void setStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = Objects.requireNonNull(routeStrategy, "RouteStrategy cannot be null");
    }

    /**
     * Returns the currently configured routing strategy.
     */
    public RouteStrategy getStrategy() {
        return routeStrategy;
    }

    /**
     * Executes the routing algorithm by delegating to the current strategy.
     *
     * @param origin Starting point
     * @param destination Target destination
     * @return Generated route plan
     */
    public RoutePlan planRoute(String origin, String destination) {
        if (origin == null || origin.trim().isEmpty()) {
            throw new IllegalArgumentException("Origin must not be empty");
        }
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Destination must not be empty");
        }

        // Pure delegation to the strategy — no conditional branching!
        return routeStrategy.buildRoute(origin, destination);
    }
}
