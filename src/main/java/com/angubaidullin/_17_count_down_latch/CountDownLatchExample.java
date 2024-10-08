package com.angubaidullin._17_count_down_latch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    static CountDownLatch countDownLatch = new CountDownLatch(3);

    private static void marketStuffIsOnPlace() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Market stuff is on place");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }

    private static void everythingIsReady() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Everything is ready");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }

    private static void openMarket() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("Opening market");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }

    public static void main(String[] args) throws InterruptedException {
        new Client("Ivan", countDownLatch);
        new Client("Misha", countDownLatch);
        new Client("Elena", countDownLatch);
        new Client("Oleg", countDownLatch);
        new Client("Marina", countDownLatch);
        new Client("Maria", countDownLatch);

        marketStuffIsOnPlace();
        everythingIsReady();
        openMarket();
    }
}
