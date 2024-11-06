import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class ConcurrentDataStructuresDemo {

    // Shared blocking queue with a capacity of 10
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        // Create a thread pool with 5 producer threads and 5 consumer threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Start producer threads
        for (int i = 0; i < 5; i++) {
            executor.submit(new Producer(queue));
        }

        // Start consumer threads
        for (int i = 0; i < 5; i++) {
            executor.submit(new Consumer(queue));
        }

        // Let the threads run for some time
        Thread.sleep(5000);

        // Shut down the executor and wait for threads to finish
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("All tasks completed.");
    }
}