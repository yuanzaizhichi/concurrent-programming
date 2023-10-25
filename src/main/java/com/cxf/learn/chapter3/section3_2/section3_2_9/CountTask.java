package com.cxf.learn.chapter3.section3_2.section3_2_9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.*;

public class CountTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10000;
    private Long start;
    private Long end;

    public CountTask(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        final boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            final long step = (start + end) / 100;
            final ArrayList<CountTask> subTasks = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }
                final CountTask subTask = new CountTask(pos, lastOne);
                pos += step + 1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for (CountTask subTask : subTasks) {
                sum += subTask.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        final ForkJoinPool forkJoinPool = new ForkJoinPool();
//        final CountTask countTask = new CountTask(0L, 200000L);
//        final ForkJoinTask<Long> result = forkJoinPool.submit(countTask);
//        final Long res = result.get();
//        System.out.println("sum=" + res);


        final ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.offer(1);
        concurrentLinkedQueue.offer(2);
    }
}
