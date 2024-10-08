package com.angubaidullin._2_thread_creating_methods._2_runnable_using;

/**
 * Реализация интерфейса Runnable
 */
public class MyRunnableThreadExample1 implements Runnable{
    private int value;
    private int counter;

    public MyRunnableThreadExample1(int value, int counter) {
        this.value = value;
        this.counter = counter;
    }
    @Override
    public void run() {
        for (int i = value; i < counter; i++) {
            System.out.println(i);
        }
    }
}
