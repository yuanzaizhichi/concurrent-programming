package com.cxf.learn.chapter2;

public class DaemonDemo {
    public static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("i am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final DaemonT t = new DaemonT();
//        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);
    }
}
