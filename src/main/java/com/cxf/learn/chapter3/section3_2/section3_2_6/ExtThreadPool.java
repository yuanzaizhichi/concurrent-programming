package com.cxf.learn.chapter3.section3_2.section3_2_6;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExtThreadPool {
    public static class MyTask implements Runnable {
        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行" + ":Thread ID:" + Thread.currentThread().getId() + ",Task Name=" + name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public static void main(String[] args) throws InterruptedException {
            final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()) {
                @Override
                protected void beforeExecute(Thread t, Runnable r) {
                    System.out.println("准备执行:" + ((MyTask) r).name);
                }

                @Override
                protected void afterExecute(Runnable r, Throwable t) {
                    System.out.println("执行完成:" + ((MyTask) r).name);
                }

                @Override
                protected void terminated() {
                    System.out.println("线程池退出");
                }
            };
            for (int i = 0; i < 5; i++) {
                final MyTask myTask = new MyTask("TASK-GEYM-" + i);
                poolExecutor.execute(myTask);
                Thread.sleep(10);
            }
            poolExecutor.shutdown();
        }

//        public static void main(String[] args) {
//            System.out.println(Runtime.getRuntime().availableProcessors());
//        }
    }
}
