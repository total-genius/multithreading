package com.angubaidullin._16_semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample1 {
    public static void main(String[] args) {
        Semaphore callBox = new Semaphore(2);
        new Person("Ivan", callBox);
        new Person("Elena", callBox);
        new Person("Oleg", callBox);
        new Person("Maria", callBox);
        new Person("Vasya", callBox);
        new Person("Marina", callBox);

    }
}
