 class PoolMonitor implements Runnable {
    @Override
    public void run() {
        while (!isShutdown) {
            synchronized (CustomThreadPool.this) {
                int taskCount = taskQueue.taskQueue.size();

                // If there are more tasks than current pool size and not at max size, add more workers
                if (taskCount > currentPoolSize && currentPoolSize < maxPoolSize) {
                    int newWorkers = Math.min(taskCount - currentPoolSize, maxPoolSize - currentPoolSize);
                    for (int i = 0; i < newWorkers; i++) {
                        Worker worker = new Worker(taskQueue);
                        workers.add(worker);
                        worker.start();
                    }
                    currentPoolSize += newWorkers;
                    System.out.println("Increased pool size to: " + currentPoolSize);
                }

                // If there are fewer tasks and more than min pool size, reduce workers
                if (taskCount < currentPoolSize && currentPoolSize > minPoolSize) {
                    int workersToRemove = Math.min(currentPoolSize - minPoolSize, currentPoolSize - taskCount);
                    for (int i = 0; i < workersToRemove; i++) {
                        Worker worker = workers.remove(workers.size() - 1);
                        worker.interrupt();
                    }
                    currentPoolSize -= workersToRemove;
                    System.out.println("Decreased pool size to: " + currentPoolSize);
                }
            }

            try {
                // Sleep for a bit before the next check to avoid busy waiting
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

// Main method to test the custom thread pool
public static void main(String[] args) throws InterruptedException {
    CustomThreadPool pool = new CustomThreadPool(3, 10);

    // Submit tasks to the pool
    for (int i = 0; i < 20; i++) {
        pool.submit(() -> {
            try {
                Thread.sleep(500);  // Simulate work
                System.out.println(Thread.currentThread().getName() + " completed a task");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    // Let the tasks execute for some time
    Thread.sleep(5000);

    // Shutdown the pool
    pool.shutdown();
    System.out.println("Thread pool shut down.");
}
}