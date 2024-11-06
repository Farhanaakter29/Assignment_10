import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CustomThreadPool {
    private final int minPoolSize;
    private final int maxPoolSize;
    private volatile int currentPoolSize;
    private final List<Worker> workers;
    private final TaskQueue taskQueue;
    private boolean isShutdown = false;

    public CustomThreadPool(int minPoolSize, int maxPoolSize) {
        this.minPoolSize = minPoolSize;
        this.maxPoolSize = maxPoolSize;
        this.currentPoolSize = minPoolSize;
        this.taskQueue = new TaskQueue();
        this.workers = new LinkedList<>();

        // Start the initial set of worker threads
        for (int i = 0; i < minPoolSize; i++) {
            Worker worker = new Worker(taskQueue);
            workers.add(worker);
            worker.start();
        }

        // Monitor thread to dynamically resize the pool based on the workload
        new Thread(new PoolMonitor()).start();
    }

    // Task submission method
    public void submit(Runnable task) {
        if (!isShutdown) {
            taskQueue.addTask(task);
        } else {
            throw new IllegalStateException("ThreadPool is shut down, cannot submit tasks.");
        }
    }

    // Method to shut down the thread pool gracefully
    public synchronized void shutdown() {
        isShutdown = true;
        for (Worker worker : workers) {
            worker.interrupt();
        }
    }