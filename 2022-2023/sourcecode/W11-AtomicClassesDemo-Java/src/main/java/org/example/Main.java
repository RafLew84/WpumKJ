package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final int COUNT_UP_TO = 1000;
    private static final int NUM_OF_THREADS = 100;

    private static volatile int mCount;
    //private static final AtomicInteger mCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        mCount = 0;
        //mCount.set(0);

        for(int i = 0; i < NUM_OF_THREADS; i++){
            startThread();
        }
        Thread.sleep(2000);
        System.out.println(mCount);
        //System.out.println(mCount.get());
    }

    private static void startThread() {
        new Thread(() ->{
            for(int i = 0; i < COUNT_UP_TO; i++){
                mCount++;
                //mCount.incrementAndGet();
            }
        }).start();
    }
}