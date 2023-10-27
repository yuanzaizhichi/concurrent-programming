package com.cxf.learn.chapter8;

import java.util.ArrayList;

public class UnsafeArrayList {
    static ArrayList al = new ArrayList();

    static class AddTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 1000000; i++) {
                al.add(new Object());
            }
        }
    }

    public static void main(String[] args) {
        final Thread t1 = new Thread(new AddTask(), "t1");
        final Thread t2 = new Thread(new AddTask(), "t2");
        t1.start();
        t2.start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t3").start();
    }
}
