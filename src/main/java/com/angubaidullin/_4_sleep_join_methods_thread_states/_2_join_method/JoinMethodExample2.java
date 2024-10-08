package com.angubaidullin._4_sleep_join_methods_thread_states._2_join_method;

public class JoinMethodExample2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "- in this thread has just started main method");
        Thread t = new Thread(()-> {
            System.out.println("Some work has just begun in thread "+ Thread.currentThread().getName());
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Some work has just ended in thread "+ Thread.currentThread().getName());
        });

        t.start();
        t.join(1500); // Будет ждать пока, поток не завершит работу или пока не пройдет указанное в аргументах время
        System.out.println(Thread.currentThread().getName() + "- in this thread has just ended main method");
    }
}
