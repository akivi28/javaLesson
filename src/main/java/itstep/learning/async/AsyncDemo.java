package itstep.learning.async;

import java.util.Objects;
import java.util.concurrent.*;

public class AsyncDemo {
    private final ExecutorService pool = Executors.newFixedThreadPool(3);
    public void run() {
        //percentDemo();
        taskDemo();
    }

    private void taskDemo() {

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try{ TimeUnit.MILLISECONDS.sleep(500);}
                catch (InterruptedException ignore) {}
                return "Callable data";
            }
        };
        Callable<String> callable2 = () -> {
            try{ TimeUnit.MILLISECONDS.sleep(500);}
            catch (InterruptedException ignore) {}
            return "Callable2 data";
        };
        Future<String> task1 = pool.submit( callable );
        Future<String> task2 = pool.submit( callable2 );
        Future<Double> task3 = pool.submit( () -> getPercent(1) );
        try {
            String res1 = task1.get();
            System.out.println(res1);
            String res2 = task2.get();
            System.out.println(res2);
            double res3 = task3.get();
            System.out.println(res3);

            sum = 100.0;
            Future<Double>[] tasks = new Future[12];
            for (int i = 1; i <= 12; i++) {
                tasks[i-1] = pool.submit( new PercentCallable(i) );
            }
            for (int i = 1; i <= 12; i++) {
                double percent = tasks[i-1].get();
                double factor = 1.0 + percent / 100.0;
                sum = sum * factor;
                System.out.printf( "month: %d, sum: %.2f\n", i, sum );
            }

        }
        catch (InterruptedException | ExecutionException e) {
            System.err.println( e.getClass().getSimpleName() + ": " + e.getMessage() );
        }

        pool.shutdown();
        try { pool.awaitTermination(15000, TimeUnit.MILLISECONDS); }
        catch (InterruptedException ignore) {}
        pool.shutdownNow();

    }

    class PercentCallable implements Callable<Double> {
        private final int month;
        public PercentCallable(int month) {
            this.month = month;
        }
        @Override
        public Double call() throws Exception {
            double percent = getPercent(month);
            // double factor = 1.0 + percent / 100.0;
            // sum = sum * factor;
            // System.out.printf( "month: %d, sum: %.2f\n", month, sum );
            return percent;
        }
    }

    private double sum ;
    private final Object sumLocker = new Object();
    private int threadsCountdown;

    private void percentDemo() {
        sum = 100.0;
        threadsCountdown = 12;
        Thread[] threads = new Thread[threadsCountdown];
        for (int i = 1; i <= threadsCountdown; i++) {
            threads[i-1] = new Thread( new PercentRunnable(i) );
            threads[i-1].start();
        }
        for (int i = 1; i <= 12; i++) {
            try{ threads[i-1].join(); }
            catch( InterruptedException ignore ) {}
        }
        System.out.printf( "=============\ntotal: %.3f\n", sum );
    }

    private class PercentRunnable implements Runnable {
        private final int month;

        public PercentRunnable(int month) {
            this.month = month;
        }

        @Override
        public void run() {
            double factor = 1.0 + getPercent(month) / 100.0;
            double localSum;
            int localCountdown;
            synchronized (sumLocker) {
                sum = sum * factor;
                localSum = sum;
                localCountdown = --threadsCountdown;
            }
            System.out.printf( "month: %d, sum: %.2f\n", month, localSum );

            if( localCountdown == 0 ) {
                System.out.printf( "----------------\ntotal: %.3f\n", localSum );
            }
        }
    }

    private double getPercent(int month) {
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        }
        catch (InterruptedException ignore) { }
        return 10.0;
    }

    private String strFromThread;

    private void threadDemo() {
        Runnable runnable = new Runnable() {
            public void run() {
                strFromThread = "Hello from Runnable";
            }
        };
        Thread thread = new Thread(runnable);

        thread.start();
        // thread.run();
        System.out.println("Hello from Main");
        System.out.println("Hello from Main");

        try { thread.join(); }
        catch (InterruptedException ignore) {  }
        System.out.println(strFromThread);

        System.out.println("Hello from Main");
    }



}
