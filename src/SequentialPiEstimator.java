import java.util.Random;

public class SequentialPiEstimator implements PiEstimator {

    @Override
    public double estimatePi(SimulationConfig config) {

        // validate the configuration
        if (config == null) {
            throw new IllegalArgumentException("Config cannot be null");
        }

        // get the total number of points
        long totalPoints = config.getTotalPoints();
        if (totalPoints <= 0) {
            throw new IllegalArgumentException(
                "Total points must be greater than zero"
            );
        }

        // count points inside the circle
        long pointsInsideCircle = 0;
        Random random = new Random();

        for (long i = 0; i < totalPoints; i++) {

            // x and y between -1 and 1
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;

            // check if the point is inside the circle
            if (x * x + y * y <= 1) {
                pointsInsideCircle++;
            }
        }

        // pi
        double piEstimate =
                4.0 * pointsInsideCircle / totalPoints;

        return piEstimate;
    }
}