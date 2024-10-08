package com.angubaidullin._15_callable_and_future._1_callable_future_factorial_example;

import java.util.concurrent.*;

public class CallableFutureExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CallableFactorial factorial = new CallableFactorial(5);
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = service.submit(factorial);
        System.out.println(future.isDone());
        System.out.println("We want to get result");
        int result = future.get();
        System.out.println("We got result");
        System.out.println(future.isDone());
        System.out.println(result);
        service.shutdown();

    }
}
