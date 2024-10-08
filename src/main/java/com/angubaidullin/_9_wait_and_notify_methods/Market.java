package com.angubaidullin._9_wait_and_notify_methods;

public class Market {
    private int breadCount;

    public synchronized void getBread() {
        while (breadCount < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        breadCount--;
        System.out.println("Client bought 1 bread");
        System.out.println("bread left: " + breadCount);
        notify();
    }

    public synchronized void putBread() {
        while (breadCount > 4) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        breadCount++;
        System.out.println("Baker put 1 bread");
        System.out.println("bread in market: " + breadCount);
        notify();
    }


}
