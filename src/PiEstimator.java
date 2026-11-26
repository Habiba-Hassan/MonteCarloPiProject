public interface PiEstimator {

    double estimatePi(SimulationConfig config);

    default String getName() {
        return getClass().getSimpleName();
    }
}
