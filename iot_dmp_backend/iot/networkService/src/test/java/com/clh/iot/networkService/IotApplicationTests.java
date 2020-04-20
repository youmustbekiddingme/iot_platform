package com.clh.iot.networkService;

import com.clh.iot.networkService.dao.UserMapper;
import com.clh.iot.networkService.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotApplication.class)
public class IotApplicationTests {
    @Autowired
    private UserMapper userMapper;

	@Test
	public void test(){
	    for(int i=0;i<23;i++){
            User user    = new User();
            user.setRealname("caolihua"+i);
            user.setRegtime(new Date());
            user.setAddress("深圳龙华民治");
            user.setEmail("545018520@qq.com");
            user.setIsonline("在线");
            user.setNickname("clh" + i);
            user.setPhone("18320966643");
            userMapper.insert(user);
        }


    }
    @Test
    public void test1(){

    }
}
