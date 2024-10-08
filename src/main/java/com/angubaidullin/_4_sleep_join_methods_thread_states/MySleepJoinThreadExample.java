package com.angubaidullin._4_sleep_join_methods_thread_states;

public class MySleepJoinThreadExample implements Runnable {
    private int value;
    private int counter;

    public MySleepJoinThreadExample(int value, int counter) {
        this.value = value;
        this.counter = counter;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starts");
        for (int i = value; i < counter; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(i+"-"+ Thread.currentThread().getName());
        }
    }
}
