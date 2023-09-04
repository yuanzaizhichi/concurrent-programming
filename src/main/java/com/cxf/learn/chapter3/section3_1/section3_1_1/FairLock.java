package com.cxf.learn.chapter3.section3_1.section3_1_1;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get the lock!");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final FairLock fairLock = new FairLock();
        final Thread t1 = new Thread(fairLock, "thread-1");
        final Thread t2 = new Thread(fairLock, "thread-2");
        t1.start();
        t2.start();
    }
}
