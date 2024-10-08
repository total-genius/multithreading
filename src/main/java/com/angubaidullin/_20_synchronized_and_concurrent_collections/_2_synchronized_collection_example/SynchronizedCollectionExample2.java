package com.angubaidullin._20_synchronized_and_concurrent_collections._2_synchronized_collection_example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SynchronizedCollectionExample2 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        List<Integer> syncList =
                Collections.synchronizedList(list);

        Runnable runnable = ()->{
            synchronized (syncList) {
                Iterator<Integer> iterator = syncList.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }

        };

        Runnable runnable2 = () -> {
            syncList.remove(1);
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(syncList);



    }
}
