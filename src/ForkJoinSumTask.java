
import java.util.concurrent.RecursiveTask;
class ForkJoinSumTask extends RecursiveTask<Long> {
    private static final double THRESHOLD=10000;
    private final int[] array;
    private final int start;
    private final int end;

    public ForkJoinSumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            // Base case: sum the subarray directly
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Split the task into two subtasks
            int mid = start + length / 2;
            ForkJoinSumTask leftTask = new ForkJoinSumTask(array, start, mid);
            ForkJoinSumTask rightTask = new ForkJoinSumTask(array, mid, end);

            // Fork the subtasks and compute them in parallel
            leftTask.fork();  // Fork the left subtask
            long rightResult;  // Directly compute the right task
            rightResult = rightTask.compute();
            long leftResult = leftTask.join();  // Wait for the left task to complete

            // Combine the results
            return leftResult + rightResult;
        }
    }
}
