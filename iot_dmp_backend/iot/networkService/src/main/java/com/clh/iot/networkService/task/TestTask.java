package com.clh.iot.networkService.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestTask  extends   Thread{


    @Override
    public void run() {
        System.out.println(System.currentTimeMillis());
    }

    public static void main(String[] args) {
         ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        TestTask testTask = new TestTask();
        testTask.setName("caolihuaTest");
        ses.scheduleAtFixedRate(testTask,1,5, TimeUnit.MILLISECONDS);
    }
}
