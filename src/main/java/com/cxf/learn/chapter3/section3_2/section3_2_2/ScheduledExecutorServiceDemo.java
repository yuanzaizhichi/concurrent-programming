package com.cxf.learn.chapter3.section3_2.section3_2_2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        final ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleWithFixedDelay(() -> {
            try {
//                Thread.sleep(1000);
                Thread.sleep(8000);
                System.out.println(System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
