package com.clh.iot.networkService.test;

public class XXX implements  Runnable {
    int i =0;
    @Override
    public void run() {
        i++;
       int a =1;
            int b=   a/0;

        System.out.println("被执行了"+System.currentTimeMillis()/1000+"次数"+i);
    }
}
