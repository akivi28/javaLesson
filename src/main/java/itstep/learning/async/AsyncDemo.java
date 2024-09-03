package itstep.learning.async;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AsyncDemo {
    public void run() {
        percentDemo();
        taskDemo();
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


    private void taskDemo() {

    }
}
