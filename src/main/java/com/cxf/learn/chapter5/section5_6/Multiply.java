package com.cxf.learn.chapter5.section5_6;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Multiply implements Runnable {
    public static BlockingDeque<Msg> bq = new LinkedBlockingDeque<Msg>();

    @Override

    public void run() {
        for (; ; ) {
            try {
                final Msg msg = bq.take();
                msg.i = msg.i * msg.j;
                Div.bq.add(msg);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
