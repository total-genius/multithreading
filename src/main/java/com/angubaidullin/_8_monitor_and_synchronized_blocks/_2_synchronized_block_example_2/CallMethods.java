package com.angubaidullin._8_monitor_and_synchronized_blocks._2_synchronized_block_example_2;

public class CallMethods {

    static final Object lock = new Object();

    void mobileCall() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Mobile call start");
            Thread.sleep(2000);
            System.out.println("Mobile call end");
        }

    }

    void skypeCall() throws InterruptedException {
        synchronized (lock) {
            System.out.println("skype call start");
            Thread.sleep(2000);
            System.out.println("skype call end");
        }
    }

    void whatsAppCall() throws InterruptedException {
        synchronized (lock) {
            System.out.println("whatsApp call start");
            Thread.sleep(2000);
            System.out.println("whatsApp call end");
        }
    }

}
