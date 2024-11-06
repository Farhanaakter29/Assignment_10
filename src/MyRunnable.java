class MyRunnable implements Runnable {
    @Override
    public void run() {
        // Print the name of the current thread
        System.out.println("Current thread: " + Thread.currentThread().getName());
    }
}