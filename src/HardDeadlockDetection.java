import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
public class HardDeadlockDetection {

    // Define two locks
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // Create two threads that can potentially cause a deadlock
        Thread threadA = new Thread(new TaskA(), "Thread A");
        Thread threadB = new Thread(new TaskB(), "Thread B");

        // Start both threads
        threadA.start();
        threadB.start();

        // Allow some time for the deadlock to occur
        Thread.sleep(1000);

        // Detect deadlock using ThreadMXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreadIds = threadMXBean.findDeadlockedThreads();

        if (deadlockedThreadIds != null) {
            System.out.println("Deadlock detected!");
            for (long threadId : deadlockedThreadIds) {
                System.out.println("Thread with ID: " + threadId + " is in deadlock.");
            }

            // Attempt to recover from deadlock by interrupting the deadlocked threads
            System.out.println("Attempting to recover from deadlock...");
            threadA.interrupt();
            threadB.interrupt();
        } else {
            System.out.println("No deadlock detected.");
        }

        // Wait for both threads to finish execution
        threadA.join();
        threadB.join();
    }
}

