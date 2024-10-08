package com.angubaidullin._7_data_race_and_synchronized_methods;

public class DataRaceAndSynchronizedThreadExample {
    public static void main(String[] args) {
        DataRaceAndSynchronizedThread runnable = new DataRaceAndSynchronizedThread();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();

    }
}
