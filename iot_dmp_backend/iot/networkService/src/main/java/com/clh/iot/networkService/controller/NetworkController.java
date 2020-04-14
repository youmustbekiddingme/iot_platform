package com.clh.iot.networkService.controller;

import com.clh.iot.networkService.task.TcpClientTask;
import com.clh.iot.networkService.task.TcpServerTask;
import com.clh.iot.networkService.task.UdpClientTask;
import com.clh.iot.networkService.task.UdpServerTask;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class NetworkController {
    private static final Logger logger =
            LoggerFactory.getLogger("networkService");
    @Autowired
    private TcpServerTask tcpServerTaskTask;
    @Autowired
    private TcpClientTask tcpClientTask;
    @Autowired
    private UdpServerTask udpServerTask;
    @Autowired
    private UdpClientTask udpClientTask;

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

    /**
     * 开启TCP-SERVER
     * @return
     */
    @GetMapping("/onTcpServer")
    public Object tcpServerNetty(){
        Map map = new HashMap();map.put("开启进程","TCP-SERVER");
        System.out.println("开启TCP服务端进程");
        executeTask(tcpServerTaskTask);
        return map;
    }

    @GetMapping("/onTcpClient")
    public Object tcpClientNetty(){
        Map map = new HashMap();map.put("开启进程","TCP-CLIENT");
        System.out.println("开启TCP客户端进程");
        executeTask(tcpClientTask);
        return map;
    }
    /**
     * 开启UDP-SERVER
     * @return
     */
    @GetMapping("/onUdpServer")
    public Object udpServerNetty(){
        Map map = new HashMap();map.put("开启进程","UDP-SERVER");
        System.out.println("开启UDP进程");

        executeTask(udpServerTask);

        return map;
    }

    /**
     * 开启Udp-CLIENT
     * @return
     */
    @GetMapping("/onUdpClient")
    public Object udpClientNetty(){
        Map map = new HashMap();map.put("开启进程","UDP-CLIENT");
        System.out.println("开启UDP-CLIENT进程");

        executeTask(udpClientTask);

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
