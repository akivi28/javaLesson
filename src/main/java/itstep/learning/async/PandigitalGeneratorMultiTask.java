package itstep.learning.async;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class PandigitalGeneratorMultiTask {
    private final ExecutorService pool = Executors.newFixedThreadPool(10);
    private final StringBuilder sharedBuffer = new StringBuilder();
    private final Set<Integer> usedDigits = new HashSet<>();
    private final Object lock = new Object();
    private int currentIndex = 0;

    public static void main(String[] args) {
        new PandigitalGenerator().run();
    }

    public void run() {
        try {
            Future<String>[] tasks = new Future[10];

            for (int i = 0; i < 10; i++) {
                tasks[i] = pool.submit(new PandigitalCallable(i));
            }

            for (Future<String> task : tasks) {
                task.get();
            }

            System.out.println("Final pandigital number: " + sharedBuffer.toString());
        }
        catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        }
        finally {
            pool.shutdown();
            try {
                pool.awaitTermination(1, TimeUnit.MINUTES);
            }
            catch (InterruptedException ignore) {}
        }
    }

    private class PandigitalCallable implements Callable<String> {
        private final int digit;

        public PandigitalCallable(int digit) {
            this.digit = digit;
        }

        @Override
        public String call() throws Exception {
            synchronized (lock) {
                if (!usedDigits.contains(digit) && currentIndex < 10) {
                    sharedBuffer.append(digit);
                    usedDigits.add(digit);
                    currentIndex++;
                    System.out.println("Added digit: " + digit + " Buffer: " + sharedBuffer.toString());
                }
                return sharedBuffer.toString();
            }
        }
    }
}
