package com.cxf.learn.chapter2;

import java.util.ArrayList;

public class ArrayListMultiThread {
    static ArrayList<Integer> al = new ArrayList<>(10);

    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final AddThread t1 = new AddThread();
        final AddThread t2 = new AddThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
    }
}
