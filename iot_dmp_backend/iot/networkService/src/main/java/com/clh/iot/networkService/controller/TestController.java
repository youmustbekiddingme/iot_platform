package com.clh.iot.networkService.controller;

import com.clh.iot.networkService.task.TestTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class TestController {

    private ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
    @GetMapping("/t1")
    public Object test(){
        TestTask testTask = new TestTask();
        testTask.setName("caolihuaTest");
        ses.scheduleAtFixedRate(testTask,1,5, TimeUnit.SECONDS);
        Map map = new HashMap();
        map.put("mao", "fafa");
        return map;
    }

}
