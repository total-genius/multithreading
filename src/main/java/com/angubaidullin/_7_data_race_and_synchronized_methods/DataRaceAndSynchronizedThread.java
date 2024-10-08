package com.angubaidullin._7_data_race_and_synchronized_methods;

import com.angubaidullin.classes_for_demonstrate_examples.Counter;

public class DataRaceAndSynchronizedThread implements Runnable {

    public synchronized void increment(int n) {
        Counter.setCount(n);
        System.out.print(Counter.getCount()+" by thread ("+Thread.currentThread().getName()+") ");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            increment(1);
        }
    }
}
