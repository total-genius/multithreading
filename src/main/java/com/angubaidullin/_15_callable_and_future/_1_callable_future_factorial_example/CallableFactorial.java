package com.angubaidullin._15_callable_and_future._1_callable_future_factorial_example;

import java.util.concurrent.Callable;

public class CallableFactorial implements Callable<Integer> {
    private int n;
    public CallableFactorial(int n) {
        this.n = n;
    }
    @Override
    public Integer call() throws Exception {
        if (n <= 0) {
            throw new Exception("invalid n");
        }
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
            Thread.sleep(500);

        }
        return fact;
    }
}
