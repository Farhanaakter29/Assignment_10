public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable(), "Thread-A");
        Thread thread2 = new Thread(new MyRunnable(), "Thread-B");
        Thread thread3 = new Thread(new MyRunnable(), "Thread-C");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}