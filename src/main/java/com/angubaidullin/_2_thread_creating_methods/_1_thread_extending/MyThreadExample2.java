package com.angubaidullin._2_thread_creating_methods._1_thread_extending;

/***
 * 1)Наследуем наш класс от класса Thread
 * 2)Реализуем метод run()
 */
public class MyThreadExample2 extends Thread {
    public void run() {
        for (int i = -10000; i < 0; i++) {
            System.out.println(i);
        }
    }
}
