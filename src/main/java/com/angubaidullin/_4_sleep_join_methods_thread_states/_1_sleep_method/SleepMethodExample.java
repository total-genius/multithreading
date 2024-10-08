package com.angubaidullin._4_sleep_join_methods_thread_states._1_sleep_method;

public class SleepMethodExample {
    //Усыпим поток main
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(i);

        }
    }
}
