// If your files inside src/ use a package declaration, place it at the very top, e.g.:
// package montecarlo;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class ParallelTask implements Callable<Long> {

    private final long numPoints;

    public ParallelTask(long numPoints) {
        if (numPoints < 0) {
            throw new IllegalArgumentException("Number of points must be non-negative.");
        }
        this.numPoints = numPoints;
    }

    @Override
    public Long call() {
        long insideCircle = 0L;
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (long i = 0; i < numPoints; i++) {
            // Generate random coordinates within [-1.0, 1.0)
            double x = random.nextDouble(-1.0, 1.0);
            double y = random.nextDouble(-1.0, 1.0);

            // Check if the point falls inside the unit circle (x^2 + y^2 <= 1)
            if ((x * x) + (y * y) <= 1.0) {
                insideCircle++;
            }
        }

        return insideCircle;
    }
}
