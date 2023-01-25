package org.example;

public class Main {
    private static int sCounter = 0;

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        new Consumer().start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            return;
        }
        new Producer().start();
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            int localCounter = -1;
            while (true) {
                synchronized (LOCK) {
                    if (localCounter != sCounter) {
                        System.out.println("Consumer: " + sCounter);
                        localCounter = sCounter;
                    }
                    if (sCounter >= 7)
                        break;

                    LOCK.notifyAll();

                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

            }
            System.out.println("Consumer is terminated");
        }
    }

    static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if(sCounter >= 7)
                        break;
                    int localValue = sCounter;
                    localValue++;
                    System.out.println("Producer: " + localValue);
                    sCounter = localValue;

                    LOCK.notifyAll();

                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        return;
                    }

                }
            }
            System.out.println("Producer is terminated");
        }
    }
}
