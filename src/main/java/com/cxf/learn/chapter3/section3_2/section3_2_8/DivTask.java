package com.cxf.learn.chapter3.section3_2.section3_2_8;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivTask implements Runnable {
    int a, b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double re = a / b;
        System.out.println(re);
    }

    public static void main(String[] args) {
        final ThreadPoolExecutor pools = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
//            pools.submit(new DivTask(100, i));
            pools.execute(new DivTask(100, i));
        }
    }
}
