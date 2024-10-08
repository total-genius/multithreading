package com.angubaidullin._15_callable_and_future._1_callable_future_factorial_example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Factorial {
    static int factorialResult;

    public static void main(String[] args) throws InterruptedException {

        Runnable factorial = new Runnable() {
            int f=5;


            @Override
            public void run() {
                if (f <= 0) {
                    System.out.println("incorrect value");
                    return;
                }
                int result =1;
                for (int i = 1; i <= f; i++) {
                    result *= i;
                }
                factorialResult = result;
            }
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(factorial);
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println(factorialResult);

    }
}
