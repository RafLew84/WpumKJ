package org.example;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    final static int sum = 17;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<BigInteger> callable;
        callable = new Callable<BigInteger>() {
            @Override
            public BigInteger call() {
                BigInteger result = BigInteger.ZERO;
                for (int i = 0; i <= sum; i++) {
                    result = result.add(BigInteger.valueOf(i));
                }
                return result;
            }
        };

        Future<BigInteger> taskFuture = executor.submit(callable);
        try {
            while (!taskFuture.isDone())
                System.out.println("waiting");
            System.out.println(taskFuture.get());
        } catch (
                ExecutionException ee) {
            System.err.println("task threw an exception");
            ee.printStackTrace();
        } catch (
                InterruptedException ie) {
            System.err.println("interrupted while waiting");
        }
        executor.shutdown();
    }
}