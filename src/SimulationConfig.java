public class SimulationConfig {

    private final long totalPoints;
    private final int numTasks;
    private final int numThreads;

    public SimulationConfig(long totalPoints, int numTasks, int numThreads) {
        if (totalPoints <= 0) {
            throw new IllegalArgumentException("totalPoints must be > 0");
        }
        if (numTasks <= 0) {
            throw new IllegalArgumentException("numTasks must be > 0");
        }
        if (numThreads <= 0) {
            throw new IllegalArgumentException("numThreads must be > 0");
        }
        if (numTasks > totalPoints) {
            throw new IllegalArgumentException("numTasks must be <= totalPoints");
        }
        this.totalPoints = totalPoints;
        this.numTasks = numTasks;
        this.numThreads = numThreads;
    }

    public long getTotalPoints() { return totalPoints; }
    public int getNumTasks()     { return numTasks; }
    public int getNumThreads()   { return numThreads; }

    
    public long getPointsForTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= numTasks) {
            throw new IndexOutOfBoundsException("taskIndex out of range: " + taskIndex);
        }
        long base = totalPoints / numTasks;
        long remainder = totalPoints % numTasks;
        return base + (taskIndex < remainder ? 1 : 0);
    }

    @Override
    public String toString() {
        return "N=" + totalPoints + ", tasks=" + numTasks + ", threads=" + numThreads;
    }
}
