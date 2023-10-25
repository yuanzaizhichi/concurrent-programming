package com.cxf.learn.chapter3.section3_2.section3_2_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
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

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        final MyTask myTask = new MyTask();
        for (int i = 0; i < 10; i++) {
            executorService.submit(myTask);
        }
    }
}
