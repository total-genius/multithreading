package com.angubaidullin._11_lock_reentrant_lock._1_lock_example;

public class LockExample {
    public static void main(String[] args) {
        PhoneCall pc = new PhoneCall();
        Thread mobileCall = new Thread(() -> pc.mobileCall());
        Thread skypeCall = new Thread(() -> pc.skypeCall());
        Thread whatsAppCall = new Thread(() -> pc.whatsAppCall());
        mobileCall.start();
        skypeCall.start();
        whatsAppCall.start();


    }
}
