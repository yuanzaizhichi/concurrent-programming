package com.cxf.learn.chapter3.section3_2.section3_2_5;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadFactoryDemo {
    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ": Thread ID" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final MyTask myTask = new MyTask();
        final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new SynchronousQueue(),
                r -> {
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    System.out.println("create " + t);
                    return t;
                });
        for (int i = 0; i < 5; i++) {
            poolExecutor.submit(myTask);
        }
        Thread.sleep(2000);
    }
}
