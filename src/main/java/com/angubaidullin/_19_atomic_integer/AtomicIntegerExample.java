package com.angubaidullin._19_atomic_integer;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increment() {
        atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                increment();

            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                increment();

            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                increment();

            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(atomicInteger.get());
    }
}
