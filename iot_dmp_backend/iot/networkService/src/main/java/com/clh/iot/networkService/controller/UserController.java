package com.clh.iot.networkService.controller;

import com.clh.iot.networkService.netty.packloss.UDPServer;
import com.clh.iot.networkService.task.TcpTask;
import com.clh.iot.networkService.task.UdpTask;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@RestController
public class UserController{

    @Autowired
    private TcpTask tcpTask;
    @Autowired
    private UdpTask udpTask;


    @PostMapping("/login")
    public Object loginController( @RequestBody String body){
        Gson gson = new Gson();
        Map mapP =gson.fromJson(body,Map.class);
        Map<String,String> bodyMap =  gson.fromJson((String)mapP.get("body"),Map.class);
        String username=bodyMap.get("username");
        String password=bodyMap.get("password");

        Map mapR = new HashMap();
        //模拟用户名密码输入正确
        if(username.equals("caolihua")&&password.equals("111111")){
            mapR.put("result",1);
            return mapR;
        }
        //模拟用户名密码输入错误
        mapR.put("result",2);
        return  mapR;

    }

    @GetMapping("/tcp")
    public Object tcpNetty(){
        Map map = new HashMap();map.put("开启进程","TCP-SERVER");
        System.out.println("开启TCP进程");
        executeTask(tcpTask);
        return map;
    }

    @GetMapping("/udp")
    public Object udpNetty(){
        Map map = new HashMap();map.put("开启进程","UDP-SERVER");
        System.out.println("开启UDP进程");

        executeTask(udpTask);

        return map;
    }

    /**
     *  执行异步任务
     * @param callable
     */
    private void executeTask(Callable callable){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        executor.submit(futureTask);
        executor.shutdown();
    }
}
