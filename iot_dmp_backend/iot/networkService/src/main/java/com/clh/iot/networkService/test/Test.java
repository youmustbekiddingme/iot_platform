package com.clh.iot.networkService.test;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws Exception{
//        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
//        ses.scheduleAtFixedRate(new XXX(),1,1, TimeUnit.SECONDS);
        Map map = new HashMap();
         String xxx=(String)    map.get("xxx");
         int yy = Integer.parseInt(xxx);
        System.out.println(yy);
    }
}
