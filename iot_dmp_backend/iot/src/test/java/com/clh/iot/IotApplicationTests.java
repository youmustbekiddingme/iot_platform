package com.clh.iot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IotApplicationTests {

	@Test
	void contextLoads() {
		Long time =  System.currentTimeMillis();
		//   1585813982380
		System.out.println(time);
	}

}
