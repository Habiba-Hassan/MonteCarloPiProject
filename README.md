# Monte Carlo Pi Estimation

A Java-based project that estimates the mathematical constant **π (Pi)** using the Monte Carlo method, with both sequential and parallel execution approaches.

The project demonstrates how parallel programming and multithreading can improve computational performance by distributing independent tasks across multiple threads.

---

## 📌 Project Overview

The Monte Carlo method is a probabilistic technique used to estimate numerical values through repeated random sampling.

In this project, random points are generated within a square containing a unit circle. By calculating the ratio of points that fall inside the circle to the total number of generated points, we can approximate π.

The project implements two approaches:

- **Sequential Execution:** Processes all generated points in a single execution flow.
- **Parallel Execution:** Divides the workload into independent tasks and executes them concurrently using Java's concurrency utilities.

## 🎯 Project Objectives

- Apply the Monte Carlo simulation method to estimate π.
- Implement sequential and parallel computational approaches.
- Understand Java multithreading and task execution.
- Distribute workloads efficiently across multiple tasks.
- Compare sequential and parallel execution performance.
- Explore the impact of task distribution and thread management on execution time.

## 🧮 Mathematical Foundation

The simulation generates random coordinates `(x, y)` within the square:

`-1 ≤ x ≤ 1` and `-1 ≤ y ≤ 1`

A point is considered inside the unit circle if:

`x² + y² ≤ 1`

The estimation formula is:

**π ≈ 4 × (Points Inside the Circle / Total Generated Points)**

As the number of generated points increases, the estimated value generally becomes more accurate.

## ⚙️ Project Architecture

The project is organized into the following components:

| Component | Responsibility |
|---|---|
| `PiEstimator` | Defines the common interface for Pi estimation implementations. |
| `SimulationConfig` | Stores simulation configuration, including the total number of points. |
| `SequentialPiEstimator` | Estimates Pi using sequential execution. |
| `ParallelPiEstimator` | Distributes tasks and coordinates parallel execution. |
| `ParallelTask` | Executes a portion of the simulation and returns the number of points inside the circle. |
| `PiExperimentRunner` | Coordinates experiments and supports performance evaluation. |

## 🚀 Parallel Processing Approach

The parallel implementation uses Java's concurrency framework.

### Task Distribution

The total number of points is divided among a specified number of tasks.

Each task receives a nearly equal share of the workload. Any remaining points are distributed among the first tasks to ensure that:

- No points are omitted from the workload.
- No points are assigned more than once.
- The sum of all assigned points equals the total number of points.

### Concurrent Execution

The implementation uses:

- `Callable<Long>` to represent independent computational tasks.
- `ThreadLocalRandom` to generate random coordinates.
- `ExecutorService` to manage the thread pool.
- `Future<Long>` to retrieve task results.

Each task returns its count of points inside the unit circle. The results are aggregated to calculate the final Pi estimate.

## 🛠️ Technologies Used

- **Java**
- **Java Concurrency API**
- **ExecutorService**
- **Callable and Future**
- **ThreadLocalRandom**
- **Monte Carlo Simulation**

## 📂 Project Structure

```text
MonteCarloPiProject/
│
├── src/
│   ├── PiEstimator.java
│   ├── SimulationConfig.java
│   ├── SequentialPiEstimator.java
│   ├── ParallelPiEstimator.java
│   ├── ParallelTask.java
│   └── PiExperimentRunner.java
│
└── README.md
```

## ▶️ Getting Started

### Prerequisites

- Java Development Kit (JDK)
- A Java IDE such as IntelliJ IDEA, Eclipse, or VS Code

### Running the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/Habiba-Hassan/MonteCarloPiProject.git
   ```

2. Open the project in your preferred Java IDE.

3. Ensure that all project classes are correctly configured and compiled.

4. Run the project's main entry point once the experiment runner is implemented.

## 📊 Performance Evaluation

The project is designed to compare sequential and parallel execution using different simulation configurations.

Potential evaluation metrics include:

- Estimated Pi value.
- Sequential execution time.
- Parallel execution time.
- Speedup.
- Efficiency.

**Speedup** measures the performance improvement achieved through parallel execution:

`Speedup = Sequential Execution Time / Parallel Execution Time`

## 👥 Team Project

Developed as part of a university Parallel Processing project.

The project is collaboratively implemented, with responsibilities divided among team members to cover simulation configuration, sequential execution, parallel task distribution, and experiment evaluation.

