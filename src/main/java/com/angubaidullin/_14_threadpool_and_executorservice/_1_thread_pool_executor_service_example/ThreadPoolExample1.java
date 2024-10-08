package com.angubaidullin._14_threadpool_and_executorservice._1_thread_pool_executor_service_example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main start");
//        ExecutorService executorService = new ThreadPoolExecutor(); - таким образом создают редко
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); - пул с одним потоком внутри
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Main end");
    }
}
