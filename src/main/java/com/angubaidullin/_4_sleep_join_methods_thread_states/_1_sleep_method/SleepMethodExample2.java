package com.angubaidullin._4_sleep_join_methods_thread_states._1_sleep_method;

import com.angubaidullin._4_sleep_join_methods_thread_states.MySleepJoinThreadExample;

public class SleepMethodExample2 {
    public static void main(String[] args) {
        System.out.println("Start");
        Thread t1 = new Thread(new MySleepJoinThreadExample(0, 10));
        Thread t2 = new Thread(new MySleepJoinThreadExample(-10, 0));
        t1.start();
        t2.start();
        System.out.println("end");

    }
}
