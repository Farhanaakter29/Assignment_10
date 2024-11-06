import java.util.concurrent.ForkJoinPool;
import java.util.Random;
public class ForkJoinSumExample {

    // Define a threshold for splitting the task into smaller subtasks
    private static final int THRESHOLD = 10000;

    public static void main(String[] args) {
        // Create a large array of random integers
        int size = 10_000_000;
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }

        // Measure time for the single-threaded approach
        long startTime = System.currentTimeMillis();
        long singleThreadedResult = sumArraySingleThreaded(array);
        long singleThreadedTime = System.currentTimeMillis() - startTime;

        System.out.println("Single-threaded sum: " + singleThreadedResult);
        System.out.println("Single-threaded time: " + singleThreadedTime + " ms");

        // Measure time for the Fork-Join parallel approach
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinSumTask task = new ForkJoinSumTask(array, 0, array.length);

        startTime = System.currentTimeMillis();
        long forkJoinResult = forkJoinPool.invoke(task);
        long forkJoinTime = System.currentTimeMillis() - startTime;

        System.out.println("Fork-Join sum: " + forkJoinResult);
        System.out.println("Fork-Join time: " + forkJoinTime + " ms");
    }

    // Single-threaded method to sum the array
    private static long sumArraySingleThreaded(int[] array) {
        long sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}
