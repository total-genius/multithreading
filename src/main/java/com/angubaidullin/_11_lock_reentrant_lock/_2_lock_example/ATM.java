package com.angubaidullin._11_lock_reentrant_lock._2_lock_example;

import com.angubaidullin.classes_for_demonstrate_examples.Employee;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ATM {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(new Employee("Ivan", lock));
        Thread t2 = new Thread(new Employee("Oleg", lock));
        Thread t3 = new Thread(new Employee("Elena", lock));
        Thread t4 = new Thread(new Employee("Marina", lock));
        Thread t5 = new Thread(new Employee("Huan", lock));
        Thread t6 = new Thread(new Employee("Anton", lock));

        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(4000);
        t4.start();
        t5.start();
        t6.start();


    }

}
