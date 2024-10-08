package com.angubaidullin._2_thread_creating_methods._2_runnable_using;

public class RunnableUsingExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnableThreadExample1(0, 10000));
        Thread thread2 = new Thread(new MyRunnableThreadExample1(-10000, 0));
        thread1.start();
        thread2.start();

    }
}
