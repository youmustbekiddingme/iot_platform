package com.clh.iot.networkService;

import com.clh.iot.networkService.dao.TUserMapper;
import com.clh.iot.networkService.pojo.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotApplication.class)
public class IotApplicationTests {
    @Autowired
    private TUserMapper userMapper;

	@Test
	public void test(){
	    for(int i=0;i<23;i++){
            TUser tUser = new TUser();
            tUser.setNote("hh");
            tUser.setSex(1);
            tUser.setUserName("caolihua"+i);
            userMapper.insert(tUser);
        }
    }

}
