package com.cxf.learn.chapter5.section5_5_2;

public class RealData implements Data {
    protected String result;

    public RealData(String para) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
