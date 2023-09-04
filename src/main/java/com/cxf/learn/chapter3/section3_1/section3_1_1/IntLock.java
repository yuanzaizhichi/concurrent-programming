package com.cxf.learn.chapter3.section3_1.section3_1_1;

import java.util.concurrent.locks.ReentrantLock;

public class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + " 线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread(new IntLock(1));
        final Thread t2 = new Thread(new IntLock(2));
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();
    }
}
