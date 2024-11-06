class Producer implements Runnable {
    private Buffer buffer;

    // Constructor to accept the shared buffer
    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int value = 0;
        try {
            while (true) {
                // Produce an item and add it to the buffer
                buffer.produce(value++);

                // Sleep to simulate time between producing items
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
