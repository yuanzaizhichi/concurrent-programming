package com.cxf.learn.chapter5.section5_6;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Div implements Runnable {
    public static BlockingDeque<Msg> bq = new LinkedBlockingDeque<Msg>();

    @Override

    public void run() {
        for (; ; ) {
            try {
                final Msg msg = bq.take();
                msg.i = msg.i / 2;
                System.out.println(msg.orgStr + "=" + msg.i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
