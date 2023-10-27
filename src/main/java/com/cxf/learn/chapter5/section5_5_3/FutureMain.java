package com.cxf.learn.chapter5.section5_5_3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final FutureTask<String> futureTask = new FutureTask<>(new RealData("a"));
        final ExecutorService es = Executors.newFixedThreadPool(1);
        es.submit(futureTask);
        //模拟其他业务耗时
        Thread.sleep(2000);
        System.out.println("获取数据：" + futureTask.get());
    }
}
