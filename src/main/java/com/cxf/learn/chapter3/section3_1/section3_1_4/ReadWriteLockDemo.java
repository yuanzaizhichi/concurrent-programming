package com.cxf.learn.chapter3.section3_1.section3_1_4;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        final Runnable readRunnable = () -> {
            try {
//                readWriteLockDemo.handleRead(lock);
                readWriteLockDemo.handleRead(readLock);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        final Runnable writeRunnable = () -> {
            try {
//                readWriteLockDemo.handleWrite(lock, new Random().nextInt());
                readWriteLockDemo.handleWrite(writeLock, new Random().nextInt());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        for (int i = 0; i < 20; i++) {
            new Thread(readRunnable).start();
        }
        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
    }
}
