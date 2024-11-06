 class Worker extends Thread {
    private final TaskQueue taskQueue;

    public Worker(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Runnable task = taskQueue.getTask();
                if (task != null) {
                    task.run();
                }
            }
        } catch (InterruptedException e) {
            // Worker is interrupted, allow thread to terminate
        }
    }
}
