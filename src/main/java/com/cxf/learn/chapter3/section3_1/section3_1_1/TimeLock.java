package com.cxf.learn.chapter3.section3_1.section3_1_1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " get lock success");
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getName() + " get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final TimeLock timeLock = new TimeLock();
        final Thread t1 = new Thread(timeLock);
        final Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();
    }
}
