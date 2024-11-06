import java.util.Queue;
 class TaskQueue {
    final Queue<Runnable> taskQueue = new LinkedList<>();

    public synchronized void addTask(Runnable task) {
        taskQueue.add(task);
        notify();  // Notify workers waiting for tasks
    }

    public synchronized Runnable getTask() throws InterruptedException {
        while (taskQueue.isEmpty()) {
            wait();  // Wait until a task is available
        }
        return taskQueue.poll();
    }
}