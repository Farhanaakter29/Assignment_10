
import java.util.concurrent.BlockingQueue;
class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Produce a random number
                int number = (int) (Math.random() * 100);

                // Add the number to the queue (blocks if the queue is full)
                queue.put(number);
                System.out.println("Produced: " + number);

                // Simulate delay
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}