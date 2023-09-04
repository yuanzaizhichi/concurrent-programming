package com.cxf.learn.chapter2.section2_6;

public class PriorityDemo {
    public static class HightPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("HightPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        final HightPriority hightPriority = new HightPriority();
        final LowPriority lowPriority = new LowPriority();
        hightPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        lowPriority.start();
        hightPriority.start();
    }
}
