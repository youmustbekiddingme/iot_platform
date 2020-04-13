package com.clh.iot.network.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {


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
}
