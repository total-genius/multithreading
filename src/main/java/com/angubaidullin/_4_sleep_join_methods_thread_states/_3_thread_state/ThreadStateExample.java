package com.angubaidullin._4_sleep_join_methods_thread_states._3_thread_state;

public class ThreadStateExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " - in this thread has just started main method");
        System.out.println(Thread.currentThread().getName() + " - state: " + Thread.currentThread().getState());

        Thread t = new Thread(() -> {
            System.out.println("Some work has just begun in thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Some work has just ended in thread " + Thread.currentThread().getName());
        });

        System.out.println(t.getName() + " - state: " + t.getState());

        t.start();
        t.join();

        System.out.println(t.getName() + " - state: " + t.getState());
        System.out.println(Thread.currentThread().getName() + " - in this thread has just ended main method");
    }
}
