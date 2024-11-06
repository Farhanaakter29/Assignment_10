class TaskB implements Runnable {
    @Override
    public void run() {
        try {
            // Thread B tries to acquire lock2 first, then lock1
            lock2.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " acquired lock2");

            // Simulate some work
            Thread.sleep(100);

            System.out.println(Thread.currentThread().getName() + " attempting to acquire lock1");
            lock1.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " acquired lock1");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        } finally {
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
        }
    }
}


