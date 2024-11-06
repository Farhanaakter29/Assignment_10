class MyRunnable implements Runnable {
    private String threadName;
    public MyRunnable(String name) {
        this.threadName = name;
    }
    @Override
    public void run() {
        try {
            System.out.println(threadName + " is running.");
            Thread.sleep(3000);
            System.out.println(threadName + " has woken up.");
        } catch (InterruptedException e) {
            System.out.println(threadName + " was interrupted.");
        }
    }
}
