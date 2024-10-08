package com.angubaidullin._16_semaphore;

import java.util.concurrent.Semaphore;

public class Person extends Thread {
    private String name;
    private Semaphore semaphore;
    public Person(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
        this.start();
    }
    @Override
    public void run() {
        try {
            System.out.println("Person " + name + " is waiting");
            semaphore.acquire();
            //метод пытается получить разрешение от семафора
            //метод заблокирует этот поток, пока не получит доступ у ресурсу
            System.out.println("Person " + name + " is using phone");
            Thread.sleep(3000);
            System.out.println("Person " + name + " finished");

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
            //освобождаем семафор
        }

    }
}
