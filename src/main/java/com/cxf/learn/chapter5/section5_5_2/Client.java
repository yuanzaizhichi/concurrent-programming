package com.cxf.learn.chapter5.section5_5_2;

public class Client {
    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread(() -> {
            final RealData realData = new RealData(queryStr);
            futureData.setRealData(realData);
        }).start();
        return futureData;
    }

    public static void main(String[] args) throws InterruptedException {
        final Client client = new Client();
        final Data data = client.request("name");
        //模拟其他业务耗时
        Thread.sleep(2000);
        System.out.println("获取数据：" + data.getResult());
    }
}
