package com.cxf.learn.chapter4.section4_4_5;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicStampedReferenceDemo {
//    static final AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);
//
//    public static void main(String[] args) {
//        for (int i = 0; i < 3; i++) {
//            final int timestamp = money.getStamp();
//            new Thread(() -> {
//                while (true) {
//                    final Integer m = money.getReference();
//                    if (m < 20) {
//                        if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) {
//                            System.out.println("充值成功，余额:" + money.getReference());
//                            break;
//                        }
//                        System.out.println("余额大于20，无需充值");
//                        break;
//                    }
//                }
//            }).start();
//        }
//
//        new Thread(() -> {
//            for (int i = 0; i < 100; i++) {
//                while (true) {
//                    final int timestamp = money.getStamp();
//                    final Integer m = money.getReference();
//                    if (m > 10) {
//                        if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
//                            System.out.println("消费成功，余额:" + money.getReference());
//                            break;
//                        }
//                        System.out.println("没有足够的余额，无法消费");
//                        break;
//                    }
//                }
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();
//    }

    static final AtomicReference<Integer> money = new AtomicReference<Integer>(19);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    final Integer m = money.get();
                    if (m < 20) {
                        if (money.compareAndSet(m, m + 20)) {
                            System.out.println("充值成功，余额:" + money.get());
                            break;
                        }
                        System.out.println("余额大于20，无需充值");
                        break;
                    }
                }
            }).start();
        }

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                while (true) {
                    final Integer m = money.get();
                    if (m > 10) {
                        if (money.compareAndSet(m, m - 10)) {
                            System.out.println("消费成功，余额:" + money.get());
                            break;
                        }
                        System.out.println("没有足够的余额，无法消费");
                        break;
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
