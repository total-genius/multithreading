package com.angubaidullin._8_monitor_and_synchronized_blocks._1_synchronized_block_example_1;

import com.angubaidullin.classes_for_demonstrate_examples.Counter;

public class SynchronizedBlockExample1 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public synchronized void increment(int n){
                synchronized(this){
                    Counter.setCount(n);
                    System.out.println(Counter.getCount());
                }
            }
            public void run() {
                for (int i = 0; i < 3; i++) {
                    increment(1);
                }


            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();


    }
}
