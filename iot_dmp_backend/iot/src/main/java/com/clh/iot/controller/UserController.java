package com.clh.iot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @GetMapping("/login")
    public Object loginController(){
        Map map = new HashMap();
        map.put("result",1); //1 :正确  ，0 错误  ，-1服务器异常
        return  map;
    }
}
