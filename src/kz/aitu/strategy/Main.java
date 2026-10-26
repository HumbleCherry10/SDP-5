package kz.aitu.strategy;

import kz.aitu.strategy.context.Navigator;
import kz.aitu.strategy.model.RoutePlan;
import kz.aitu.strategy.strategy.CarRouteStrategy;
import kz.aitu.strategy.strategy.CyclingRouteStrategy;
import kz.aitu.strategy.strategy.PublicTransitRouteStrategy;
import kz.aitu.strategy.strategy.RouteStrategy;
import kz.aitu.strategy.strategy.WalkingRouteStrategy;

/**
 * Client demonstrating the Strategy Pattern and runtime strategy switching.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("  Astana IT University - Software Design Patterns (Assignment #5)");
        System.out.println("  Strategy Pattern Demonstration: Multi-Modal Navigator");
        System.out.println("=============================================================\n");

        String origin = "Astana IT University (EXPO C1)";
        String destination = "Baiterek Monument";

        System.out.printf("Planning journey from [%s] to [%s]%n%n", origin, destination);

        // 1. Client configures Navigator with Car Strategy
        System.out.println(">>> 1. Configuring Context with CarRouteStrategy...");
        RouteStrategy carStrategy = new CarRouteStrategy();
        Navigator navigator = new Navigator(carStrategy);

        RoutePlan carPlan = navigator.planRoute(origin, destination);
        System.out.println(carPlan);

        // 2. Runtime Strategy Switch: Heavy downtown traffic -> switch to Public Transit
        System.out.println(">>> 2. Runtime switch: Congestion detected, switching to PublicTransitRouteStrategy...");
        navigator.setStrategy(new PublicTransitRouteStrategy());

        RoutePlan transitPlan = navigator.planRoute(origin, destination);
        System.out.println(transitPlan);

        // 3. Runtime Strategy Switch: Sunny afternoon -> switch to Cycling Strategy
        System.out.println(">>> 3. Runtime switch: Weather is clear and sunny, switching to CyclingRouteStrategy...");
        navigator.setStrategy(new CyclingRouteStrategy());

        RoutePlan cyclePlan = navigator.planRoute(origin, destination);
        System.out.println(cyclePlan);

        // 4. Runtime Strategy Switch: Short distance sightseeing -> switch to Walking Strategy
        System.out.println(">>> 4. Runtime switch: Switching to WalkingRouteStrategy for pedestrian exploration...");
        navigator.setStrategy(new WalkingRouteStrategy());

        RoutePlan walkPlan = navigator.planRoute(origin, destination);
        System.out.println(walkPlan);

        System.out.println("=============================================================");
        System.out.println("Strategy pattern demo completed successfully!");
        System.out.println("All algorithms executed polymorphically without if/else logic in Context.");
        System.out.println("=============================================================");
    }
}
