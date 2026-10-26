package kz.aitu.strategy.model;

/**
 * Encapsulates the result of a route calculation.
 */
public class RoutePlan {
    private final String origin;
    private final String destination;
    private final String transportMode;
    private final double distanceKm;
    private final int durationMinutes;
    private final double estimatedCost;
    private final String routeDetails;

    public RoutePlan(String origin,
                     String destination,
                     String transportMode,
                     double distanceKm,
                     int durationMinutes,
                     double estimatedCost,
                     String routeDetails) {
        this.origin = origin;
        this.destination = destination;
        this.transportMode = transportMode;
        this.distanceKm = distanceKm;
        this.durationMinutes = durationMinutes;
        this.estimatedCost = estimatedCost;
        this.routeDetails = routeDetails;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public String getRouteDetails() {
        return routeDetails;
    }

    @Override
    public String toString() {
        return String.format(
            "=== Route Plan [%s] ===%n" +
            "  From:        %s%n" +
            "  To:          %s%n" +
            "  Distance:    %.1f km%n" +
            "  Duration:    %d mins (%d h %d m)%n" +
            "  Est. Cost:   $%.2f%n" +
            "  Directions:  %s%n",
            transportMode,
            origin,
            destination,
            distanceKm,
            durationMinutes,
            durationMinutes / 60,
            durationMinutes % 60,
            estimatedCost,
            routeDetails
        );
    }
}
