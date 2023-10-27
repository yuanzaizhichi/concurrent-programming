package com.cxf.learn.chapter5.section5_6;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Plus implements Runnable {
    public static BlockingDeque<Msg> bq = new LinkedBlockingDeque<Msg>();

    @Override

    public void run() {
        for (; ; ) {
            try {
                final Msg msg = bq.take();
                msg.j = msg.i + msg.j;
                Multiply.bq.add(msg);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
