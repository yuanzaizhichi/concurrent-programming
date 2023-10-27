package com.cxf.learn.chapter5.section5_5_4;

import com.cxf.learn.chapter5.section5_5_3.RealData;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class FutureDemo {
    public static void main(String[] args) throws InterruptedException {
        final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        final ListenableFuture<String> task = service.submit(new RealData("x"));
        task.addListener(() -> {
            System.out.printf("异步处理成功");
            try {
                System.out.println(task.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }, MoreExecutors.directExecutor());

        System.out.println("main task done");
        Thread.sleep(3000);
    }
}
