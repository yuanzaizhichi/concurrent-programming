package com.cxf.learn.chapter2;

public class ThreadGroupName implements Runnable {

    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName() + "_" + Thread.currentThread().getName();
        while (true) {
            System.out.println("i am" + groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        final ThreadGroup tg = new ThreadGroup("PrintGroup");
        final Thread t1 = new Thread(tg, new ThreadGroupName(), "T1");
        final Thread t2 = new Thread(tg, new ThreadGroupName(), "T2");
        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();
    }
}
