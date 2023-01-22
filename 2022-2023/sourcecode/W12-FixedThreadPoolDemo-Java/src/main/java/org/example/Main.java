package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
public class Main {

    private static BigInteger calculatedSum = BigInteger.ZERO;
    private static final int NUM_THREADS = 10;

    static class Sum implements Callable<BigInteger> {
        long from, to;
        BigInteger localSum = BigInteger.ZERO;

        public Sum(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public BigInteger call() {
            for (long i = from; i <= to; i++)
                localSum = localSum.add(BigInteger.valueOf(i));
            return localSum;
        }
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<BigInteger>> summationTasks = new ArrayList<>();

        long n = 1000000L;
        long taskNum = n / 10; // 10 tasks

        for (int i = 0; i < NUM_THREADS; i++) {
            long fromInInnerRange = (taskNum * i) + 1;
            long toInInnerRange = taskNum * (i + 1);

            System.out.printf("Wątek dla sumy w zakresie %d - %d %n", fromInInnerRange, toInInnerRange);
            Callable<BigInteger> summationTask = new Sum(fromInInnerRange, toInInnerRange);
            Future<BigInteger> futureSum = executorService.submit(summationTask);
            summationTasks.add(futureSum);
        }

        for (Future<BigInteger> partialSum : summationTasks) {
            try {
                calculatedSum = calculatedSum.add(partialSum.get());
            } catch (CancellationException | ExecutionException | InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        System.out.printf("Suma = %d", calculatedSum);
        executorService.shutdown();
    }
}