package com.angubaidullin._4_sleep_join_methods_thread_states._2_join_method;

import com.angubaidullin._4_sleep_join_methods_thread_states.MySleepJoinThreadExample;

public class JoinMethodExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Thread t1 = new Thread(new MySleepJoinThreadExample(0, 5));
        Thread t2 = new Thread(new MySleepJoinThreadExample(-5, 0));
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        /*
        поток, в котором вызван метод join() - в нашем случае это поток main -
        будет ждать поток или потоки на которых вызван этот метод - в нашем случае t1,t2.
        Поток main будет ждать завершение работы потоков t1, t2 и потом продолжит
        свою работу
         */
        System.out.println("end");
    }
}
