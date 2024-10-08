package com.angubaidullin._2_thread_creating_methods._1_thread_extending;

/***
 * 1)Наследуем наш класс от класса Thread
 * 2)Реализуем метод run()
 */
public class MyThreadExample1 extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
    }
}
