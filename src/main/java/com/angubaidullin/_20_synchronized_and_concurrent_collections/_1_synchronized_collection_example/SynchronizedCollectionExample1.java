package com.angubaidullin._20_synchronized_and_concurrent_collections._1_synchronized_collection_example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollectionExample1 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> source = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            source.add(i);
        }

        List<Integer> syncTargetList =
                Collections.synchronizedList(new ArrayList<>());
        //Можно также использовать уже созданную коллекцию

        Runnable runnable = ()->syncTargetList.addAll(source);

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(syncTargetList);

    }
}
