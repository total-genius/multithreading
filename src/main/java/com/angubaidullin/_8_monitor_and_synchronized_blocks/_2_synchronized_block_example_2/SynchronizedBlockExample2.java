package com.angubaidullin._8_monitor_and_synchronized_blocks._2_synchronized_block_example_2;

public class SynchronizedBlockExample2 {
    public static void main(String[] args) {
        Runnable mobileCall = new Runnable() {
            @Override
            public void run() {
                try {
                    new CallMethods().mobileCall();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable skypeCall = new Runnable() {
            @Override
            public void run() {
                try {
                    new CallMethods().skypeCall();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        Runnable whatsAppCall = new Runnable() {
            @Override
            public void run() {
                try {
                    new CallMethods().whatsAppCall();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread mobileCallThread = new Thread(mobileCall);
        Thread skypeCallThread = new Thread(skypeCall);
        Thread whatsAppCallThread = new Thread(whatsAppCall);
        mobileCallThread.start();
        skypeCallThread.start();
        whatsAppCallThread.start();

    }
}
