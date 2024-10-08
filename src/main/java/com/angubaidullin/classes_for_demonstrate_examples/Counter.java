package com.angubaidullin.classes_for_demonstrate_examples;

public class Counter {
    private volatile static int count;
    public static int getCount(){
        return count;
    }
    public static void setCount(int n) {
        Counter.count += n;
    }
}
