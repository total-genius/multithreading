package com.angubaidullin._11_lock_reentrant_lock._1_lock_example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PhoneCall {
    private Lock lock = new ReentrantLock();

    public void mobileCall() {
        lock.lock();

        try {
            System.out.println("Mobile call start");
            Thread.sleep(2000);
            System.out.println("Mobile call end");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    public void skypeCall() {
        lock.lock();

        try {
            System.out.println("skype call start");
            Thread.sleep(2000);
            System.out.println("skype call end");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    public void whatsAppCall() {
        lock.lock();

        try {
            System.out.println("whatsApp call start");
            Thread.sleep(2000);
            System.out.println("whatsApp call end");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
}
