package com.angubaidullin._13_threads_interrupting;

public class InterruptionExample {
    public static void main(String[] args) throws InterruptedException {
        Thread interruptedThread = new Thread(){
            public void run(){
                double sqrtSum = 0;
                for (int i = 0; i < 1_000_000_000; i++) {
                    if (isInterrupted()) {
                        System.out.println("There is want to interrupt this thread.");
                        System.out.println("Проверяем состояние объектов, готовы ли они к тому" +
                                " что поток хотят прервать");
                        return;
                    }
                    sqrtSum+=Math.sqrt(i);
                }
                System.out.println(sqrtSum);
            }
        };

        System.out.println("Main start");
        interruptedThread.start();
        Thread.sleep(1000);
        interruptedThread.interrupt();
        interruptedThread.join();
        System.out.println("Main end");
    }
}
