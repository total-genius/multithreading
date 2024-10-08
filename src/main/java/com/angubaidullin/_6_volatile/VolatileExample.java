package com.angubaidullin._6_volatile;

public class VolatileExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main start");
        MyVolatileThreadExample myVolatileThreadExample = new MyVolatileThreadExample();
        Thread t1 = new Thread(myVolatileThreadExample);
        t1.start();
        Thread.currentThread().sleep(1500);
        myVolatileThreadExample.setFlag(false);
        t1.join();

        System.out.println("Main end");
    }
}
