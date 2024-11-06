public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread thread1 = new Thread(new CounterIncrementer(counter), "Thread-1");
        Thread thread2 = new Thread(new CounterIncrementer(counter), "Thread-2");
        Thread thread3 = new Thread(new CounterIncrementer(counter), "Thread-3");
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final count: " + counter.getCount());
    }
}