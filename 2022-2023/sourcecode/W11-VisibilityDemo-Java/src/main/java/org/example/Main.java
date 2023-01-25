package org.example;

public class Main {
    public static void main(String[] args) {
        new Consumer().start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            return;
        }
        new Producer().start();
    }

    private static int sCounter = 0;
    //private static volatile int sCounter = 0;

    static class Consumer extends Thread {
        @Override
        public void run() {
            int localCounter = -1;
            while (true){
                if(localCounter != sCounter){
                    System.out.println("Consumer: " + sCounter);
                    localCounter = sCounter;
                }
                if(sCounter >= 7)
                    break;
            }
            System.out.println("Consumer is terminated");
        }
    }

    static class Producer extends Thread{
        @Override
        public void run() {
            while(sCounter < 7){
                int localValue = sCounter;
                localValue++;
                System.out.println("Producer: " + localValue);
                sCounter = localValue;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println("Producer is terminated");
        }
    }
}