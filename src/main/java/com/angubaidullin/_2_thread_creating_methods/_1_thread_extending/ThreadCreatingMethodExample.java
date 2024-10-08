package com.angubaidullin._2_thread_creating_methods._1_thread_extending;

public class ThreadCreatingMethodExample {
    public static void main(String[] args) {
        MyThreadExample1 myThreadExample1 = new MyThreadExample1();
        MyThreadExample2 myThreadExample2 = new MyThreadExample2();
        //метод start() вызывает поток и запускает работу метода run()
        myThreadExample1.start();
        myThreadExample2.start();
    }
}
