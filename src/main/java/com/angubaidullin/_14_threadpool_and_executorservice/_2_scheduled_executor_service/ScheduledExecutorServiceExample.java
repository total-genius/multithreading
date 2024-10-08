package com.angubaidullin._14_threadpool_and_executorservice._2_scheduled_executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService =
                Executors.newScheduledThreadPool(1);

//        for (int i = 0; i < 10; i++) {
//            executorService.execute(
//                    () -> {
//                        System.out.println(Thread.currentThread().getName()+" has just begun work");
//                        try {
//                            Thread.sleep(500);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                        System.out.println(Thread.currentThread().getName()+" has just ended work");
//                    }
//            );
//        }

//        //Сервис выполнит переданное ему задание через 3 секунды
//        executorService.schedule(() -> {
//            System.out.println(Thread.currentThread().getName() + " has just begun work");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(Thread.currentThread().getName() + " has just ended work");
//        }, 3, TimeUnit.SECONDS);

//        //Задача начнет выпонение через 3 секунды и будет выполнятся с периодичность в 1 секунду.
//        executorService.scheduleAtFixedRate(() -> {
//            System.out.println(Thread.currentThread().getName() + " has just begun work");
//            System.out.println(Thread.currentThread().getName() + " has just ended work");
//        }, 1, 2, TimeUnit.SECONDS);
//        Thread.sleep(8000);
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + " has just begun work");
            System.out.println(Thread.currentThread().getName() + " has just ended work");
        }, 3,1,TimeUnit.SECONDS);
        Thread.sleep(8000);
        executorService.shutdown();




    }
}
