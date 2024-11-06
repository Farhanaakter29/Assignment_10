
class Task implements Runnable {
    private String taskName;

    // Constructor to give each task a name
    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is executing task: " + taskName);
        try {
            // Simulate work by sleeping for 2 seconds
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " has finished task: " + taskName);
    }
}