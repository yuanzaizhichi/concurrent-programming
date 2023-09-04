package com.cxf.learn.chapter3.section3_1.section3_1_5;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {
    public static CountDownLatch countDownLatch = new CountDownLatch(10);
    public static CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check task over");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(countDownLatchDemo);
        }
        //等待检查
        countDownLatch.await();
        //发射
        System.out.println("fire!");
        exec.shutdown();
    }
}
