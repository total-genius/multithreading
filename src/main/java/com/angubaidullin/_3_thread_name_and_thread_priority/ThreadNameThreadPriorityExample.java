package com.angubaidullin._3_thread_name_and_thread_priority;

public class ThreadNameThreadPriorityExample {
    public static void main(String[] args) {
        MyThreadNamePriorityExample thread1 = new MyThreadNamePriorityExample();
        System.out.println("name of thread1 "+ thread1.getName()+" and priority is "+thread1.getPriority());
        MyThreadNamePriorityExample thread2 = new MyThreadNamePriorityExample();
        System.out.println("name of thread2 "+ thread2.getName()+" and priority is "+thread2.getPriority());
        System.out.println("/////////////////////");
        thread1.setName("first thread");
        thread1.setPriority(8);
        thread2.setName("second thread");
        thread2.setPriority(8);
        System.out.println("name of thread1 "+ thread1.getName()+" and priority is "+thread1.getPriority());
        System.out.println("name of thread2 "+ thread2.getName()+" and priority is "+thread2.getPriority());

//        thread1.setPriority(Thread.MIN_PRIORITY);
//        thread1.setPriority(Thread.NORM_PRIORITY);
//        thread1.setPriority(Thread.MAX_PRIORITY);
//        Thread.currentThread() - Возвращает поток в котором он был вызван
    }
}
