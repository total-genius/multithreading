package com.angubaidullin._9_wait_and_notify_methods;

public class WaitNotifyMethodsExample {
    public static void main(String[] args) {
        Market market = new Market();
        Runnable producer = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    market.putBread();
                }
            }
        };

        Runnable client = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    market.getBread();
                }
            }
        };

        Thread producerThread = new Thread(producer);
        Thread clientThread = new Thread(client);
        producerThread.start();
        clientThread.start();
    }
}
