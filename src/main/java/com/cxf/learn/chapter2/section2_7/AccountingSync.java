package com.cxf.learn.chapter2.section2_7;

public class AccountingSync implements Runnable {
    static volatile int i = 0;

    public static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread(new AccountingSync());
        final Thread t2 = new Thread(new AccountingSync());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
