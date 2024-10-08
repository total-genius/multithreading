package com.angubaidullin._10_deadlock_livelock_lock_starvation._1_deadlock_example;

public class DeadLockExample {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {



        Runnable thread1 = new Runnable() {

            @Override
            public void run() {
                System.out.println("thread1 : try to get lock1");
                synchronized (lock1) {
                    System.out.println("thread1 :  got lock1");
                    System.out.println("thread1 : try to get lock2");
                    synchronized (lock2) {
                        System.out.println("thread1 : got lock2 and lock1");
                    }
                    System.out.println("thread1 : exit lock2");
                }
                System.out.println("thread1 : exit lock1");
            }
        };

        Runnable thread2 = new Runnable() {

            @Override
            public void run() {
                System.out.println("thread2 : try to get lock1");
                synchronized (lock1) {
                    System.out.println("thread2 : got lock1");
                    System.out.println("thread2 : try to get lock2");
                    synchronized (lock2) {
                        System.out.println("thread2 : got lock2 and lock1");
                    }
                    System.out.println("thread2 : exi t lock2");
                }
                System.out.println("thread2 : exit lock1");
            }
        };


        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);
        t1.start();
        t2.start();

    }

}
