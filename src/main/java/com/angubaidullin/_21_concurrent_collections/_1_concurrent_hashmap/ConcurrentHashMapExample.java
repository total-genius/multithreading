package com.angubaidullin._21_concurrent_collections._1_concurrent_hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");

        Runnable r1 = ()-> {
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Integer key = iterator.next();
                System.out.println(key + ": " + map.get(key));

            }
        };

        Runnable r2 = () -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            map.put(8, "eight");

        };

        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
