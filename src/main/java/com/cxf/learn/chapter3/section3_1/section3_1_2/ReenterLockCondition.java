package com.cxf.learn.chapter3.section3_1.section3_1_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("thread is going on");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("子线程释放锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread(new ReenterLockCondition());
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        System.out.println("主线程释放锁");
        lock.unlock();
    }
}
