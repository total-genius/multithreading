package com.angubaidullin.classes_for_demonstrate_examples;

import java.util.concurrent.locks.Lock;

public class Employee implements Runnable {
    private String name;
    private Lock lock;

    public Employee(String name, Lock lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock.tryLock()) {
            try {
                System.out.println(name + " is using ATM");
                Thread.sleep(2500);
                System.out.println(name + " finished using ATM ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(name+" doesn't want to wait. He's going away");
        }

    }
}
