package com.angubaidullin._6_volatile;

public class MyVolatileThreadExample implements Runnable {

    private volatile boolean flag ;
    private int counter;

    public MyVolatileThreadExample() {
        flag = true;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started it's work");
        while (flag) {
            counter++;
        }
        System.out.println(counter);
        System.out.println(Thread.currentThread().getName() + " finished it's work");
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
