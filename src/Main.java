import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class Main {
    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit multiple tasks to the thread pool
        for (int i = 1; i <= 10; i++) {
            Task task = new Task("Task-" + i);
            executorService.submit(task);
        }

        // Shut down the thread pool after submitting all tasks
        executorService.shutdown();

        try {
            // Wait for all tasks to finish execution or timeout after 10 seconds
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Not all tasks finished within the timeout. Forcing shutdown...");
                executorService.shutdownNow(); // Forcefully shutdown the executor
            } else {
                System.out.println("All tasks finished successfully.");
            }
        } catch (InterruptedException e) {
            System.out.println("Thread pool termination interrupted. Forcing shutdown...");
            executorService.shutdownNow();
        }
    }
}