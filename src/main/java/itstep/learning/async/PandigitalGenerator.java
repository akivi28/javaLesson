package itstep.learning.async;

public class PandigitalGenerator {
    private final Object printLock = new Object();
    private int count = 10;

    public void run() {
        pandigitalDemo();
    }

    private void pandigitalDemo() {
        count = 10;
        Thread[] threads = new Thread[count];

        for (int i = 0; i < count; i++) {
            threads[i] = new Thread(new PandigitalRunnable(i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ignore) {}
        }

        System.out.println("All threads completed.");
    }

    private class PandigitalRunnable implements Runnable {
        private final int startingDigit;

        public PandigitalRunnable(int startingDigit) {
            this.startingDigit = startingDigit;
        }

        @Override
        public void run() {
            generatePandigital("" + startingDigit);
        }

        private void generatePandigital(String currentNumber) {
            if (currentNumber.length() == 10) {
                synchronized (printLock) {
                    System.out.println("Generated number: " + currentNumber);
                }
                return;
            }

            for (int i = 0; i < 10; i++) {
                String nextDigit = String.valueOf(i);
                if (!currentNumber.contains(nextDigit)) {
                    generatePandigital(currentNumber + nextDigit);
                }
            }
        }
    }

}
