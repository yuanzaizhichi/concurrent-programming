package com.cxf.learn.chapter2.section2_2;

public class JoinMain {
    public volatile static int i = 0;

    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 10000; i++) ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final AddThread t1 = new AddThread();
        t1.start();
        t1.join();
        System.out.println(i);
    }
}
