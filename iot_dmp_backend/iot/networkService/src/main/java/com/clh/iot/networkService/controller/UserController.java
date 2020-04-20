package com.clh.iot.networkService.controller;
import com.clh.iot.networkService.dao.TUserMapper;
import com.clh.iot.networkService.pojo.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/mybatis")
@Controller("mybatisController")
public class UserController {
    @Autowired
    private TUserMapper tuserMapper;

    @ResponseBody
    @RequestMapping("/get/{id}")
    public TUser getUserById(@PathVariable Long id){
        System.out.println(tuserMapper);
        TUser user = tuserMapper.selectByPrimaryKey(id);
        return user;
    }
}
