class TaskA implements Runnable {
    @Override
    public void run() {
        try {
            // Thread A tries to acquire lock1 first, then lock2
            lock1.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " acquired lock1");

            // Simulate some work
            Thread.sleep(100);

            System.out.println(Thread.currentThread().getName() + " attempting to acquire lock2");
            lock2.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " acquired lock2");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
        }
    }
}
