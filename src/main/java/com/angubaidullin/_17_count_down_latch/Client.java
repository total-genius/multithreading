package com.angubaidullin._17_count_down_latch;

import java.util.concurrent.CountDownLatch;

public class Client extends Thread {
    private String name;
    private CountDownLatch countDownLatch;
    public Client(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
        this.start();

    }

    public void run() {
        try {
            countDownLatch.await();
            System.out.println("Client " + name + " buy goods");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //если счетчик больше 0 поток будет залочен, пока счетчик не станет 0
        //если счетчик 0 поток выполнит работу
    }
}
