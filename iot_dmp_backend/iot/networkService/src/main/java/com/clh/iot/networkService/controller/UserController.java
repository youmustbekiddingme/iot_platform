package com.clh.iot.networkService.controller;
import com.clh.iot.common.util.ParamBody;
import com.clh.iot.common.util.ResultBase;
import com.clh.iot.networkService.dao.UserMapper;
import com.clh.iot.networkService.pojo.User;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/get/{id}")
    public User getUserById(@PathVariable int id){
        System.out.println(userMapper);
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 查询多个user
     * @param body
     * @return
     */
    @RequestMapping("/searchMany")
    public ResultBase getManyUsers(@RequestBody String body){
        Map<String,String>bodyMap = ParamBody.getBodyMap(body);
        //报错，缺少必传参数
        int pageNum = Integer.parseInt(bodyMap.get("pageNum"));//页码
        int pageSize=Integer.parseInt(bodyMap.get("pageSize"));//页面大小
        PageHelper.startPage(pageNum  , pageSize);
        List<User> list = userMapper.selectManyTusers(bodyMap);
        return ResultBase.success(1,"模糊查询多个user",list);
    }

    /**
     * 查询总数
     * @return
     */
    @RequestMapping("/count")
    public ResultBase getUsersCount(){
        Integer counts= userMapper.selectCounts();
        List list = new ArrayList();
        list.add(counts);
        return ResultBase.success(1,"查询总记录数",list);
    }
    @RequestMapping("/deleteOne")
    public ResultBase deleteUserOne(@RequestBody String body){
        return null;
    }
}
