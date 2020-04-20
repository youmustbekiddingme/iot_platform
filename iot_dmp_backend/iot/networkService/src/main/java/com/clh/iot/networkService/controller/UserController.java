package com.clh.iot.networkService.controller;
import com.clh.iot.networkService.dao.TUserMapper;
import com.clh.iot.networkService.pojo.TUser;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private TUserMapper tuserMapper;

    @RequestMapping("/get/{id}")
    public TUser getUserById(@PathVariable Long id){
        System.out.println(tuserMapper);
        TUser user = tuserMapper.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 查询多个user
     * @param body
     * @return
     */
    @RequestMapping("/many")
    public Object getManyUsers(@RequestBody String body){
        Gson gson = new Gson();
        Map mapP =gson.fromJson(body,Map.class);
        Map<String,String>  bodyMap =(Map) mapP.get("body");
        PageHelper.startPage(Integer.parseInt(bodyMap.get("pageNum"))  , Integer.parseInt(bodyMap.get("pageSize")));
        List<TUser> list = tuserMapper.selectManyTusers(bodyMap);
        return list;
    }

}
