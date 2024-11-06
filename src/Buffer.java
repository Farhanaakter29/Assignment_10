import java.util.LinkedList;
import java.util.Queue;
class Buffer {
    private Queue<Integer> buffer = new LinkedList<>();
    private int capacity;

    // Constructor to set buffer capacity
    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    // Producer adds items to the buffer
    public synchronized void produce(int value) throws InterruptedException {
        while (buffer.size() == capacity) {
            // Buffer is full, producer waits
            System.out.println("Buffer is full. Producer is waiting...");
            wait();
        }

        // Add item to the buffer
        buffer.add(value);
        System.out.println("Produced: " + value);

        // Notify the consumer that the buffer is not empty anymore
        notify();
    }

    // Consumer removes items from the buffer
    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            // Buffer is empty, consumer waits
            System.out.println("Buffer is empty. Consumer is waiting...");
            wait();
        }

        // Remove item from the buffer
        int value = buffer.poll();
        System.out.println("Consumed: " + value);

        // Notify the producer that the buffer has space
        notify();
        return value;
    }
}

