
import java.util.concurrent.BlockingQueue;
 class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private int sum = 0;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Retrieve a number from the queue (blocks if the queue is empty)
                int number = queue.take();
                sum += number;
                System.out.println("Consumed: " + number + " | Current Sum: " + sum);

                // Simulate delay
                Thread.sleep(150);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
