class Consumer implements Runnable {
    private Buffer buffer;

    // Constructor to accept the shared buffer
    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Consume an item from the buffer
                buffer.consume();

                // Sleep to simulate time between consuming items
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}