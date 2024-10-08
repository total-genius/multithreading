package com.angubaidullin._12_daemon_threads;

public class DaemonThreadsExample {
    public static void main(String[] args) {
        Thread userThread = new Thread(){
            public void run(){
                System.out.println(Thread.currentThread().getName() + " is daemon? - "
                        + Thread.currentThread().isDaemon());
                for (char i = 'a'; i <= 'j'; i++) {
                    try {
                        Thread.sleep(1500);
                        System.out.println(i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread daemonThread = new Thread(){
            public void run(){
                System.out.println(Thread.currentThread().getName() + " is daemon? - "
                        + Thread.currentThread().isDaemon());
                for (int i = 1; i <= 1000; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        System.out.println("Main thread start");
        userThread.setName("userThread");
        daemonThread.setName("daemonThread");
        daemonThread.setDaemon(true);
        daemonThread.start();
        userThread.start();
        System.out.println("Main thread end");

    }
}
