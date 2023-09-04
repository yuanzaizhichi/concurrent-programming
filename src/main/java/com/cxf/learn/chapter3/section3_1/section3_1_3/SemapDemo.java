package com.cxf.learn.chapter3.section3_1.section3_1_3;

import java.util.concurrent.Semaphore;

public class SemapDemo implements Runnable {
    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ": done!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        final SemapDemo semapDemo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            new Thread(semapDemo).start();
        }
    }
}
