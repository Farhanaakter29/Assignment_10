public class Main {
    public static void main(String[] args) {
        // Create a shared buffer with a capacity of 5
        Buffer buffer = new Buffer(5);

        // Create producer and consumer threads
        Thread producerThread = new Thread(new Producer(buffer), "Producer");
        Thread consumerThread = new Thread(new Consumer(buffer), "Consumer");

        // Start the threads
        producerThread.start();
        consumerThread.start();
    }
}