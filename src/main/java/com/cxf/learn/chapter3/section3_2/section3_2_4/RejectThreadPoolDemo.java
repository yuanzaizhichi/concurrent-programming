package com.cxf.learn.chapter3.section3_2.section3_2_4;

import java.util.concurrent.*;

public class RejectThreadPoolDemo {
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
        final MyTask myTask = new MyTask();
        final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5,
                0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10), Executors.defaultThreadFactory(),
                (r, executor) -> System.out.println(r.toString() + " is discard"));

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            poolExecutor.submit(myTask);
        }
    }
}
