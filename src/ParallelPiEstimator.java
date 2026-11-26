
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelPiEstimator implements PiEstimator {

    private final int numTasks;
    private final int numThreads;

    public ParallelPiEstimator(int numTasks, int numThreads) {

        if (numTasks <= 0 || numThreads <= 0) {
            throw new IllegalArgumentException(
                "Number of tasks and threads must be greater than zero."
            );
        }

        this.numTasks = numTasks;
        this.numThreads = numThreads;
    }

    @Override
    public double estimatePi(SimulationConfig config) {

        if (config == null) {
            throw new IllegalArgumentException(
                "Config cannot be null."
            );
        }

        long totalPoints = config.getTotalPoints();

        if (totalPoints <= 0) {
            throw new IllegalArgumentException(
                "Total points must be greater than zero."
            );
        }

        long basePoints = totalPoints / numTasks;
        long remainder = totalPoints % numTasks;

        ExecutorService executor =
                Executors.newFixedThreadPool(numThreads);

        List<Future<Long>> results = new ArrayList<>();

        try {
            // Distribute points among tasks
            for (int i = 0; i < numTasks; i++) {

                long taskPoints = basePoints;

                if (i < remainder) {
                    taskPoints++;
                }

                results.add(
                    executor.submit(new ParallelTask(taskPoints))
                );
            }

            // Collect hits from all tasks
            long totalHits = 0;

            for (Future<Long> result : results) {
                totalHits += result.get();
            }

            // Calculate Pi
            return 4.0 * totalHits / totalPoints;

        } catch (InterruptedException e) {

            executor.shutdownNow();
            Thread.currentThread().interrupt();

            throw new IllegalStateException(
                "Parallel estimation was interrupted.", e
            );

        } catch (ExecutionException e) {

            throw new IllegalStateException(
                "A parallel task failed.", e
            );

        } finally {
            executor.shutdown();
        }
    }
}