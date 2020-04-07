package com.clh.iot;


import com.clh.iot.netty.packloss.UDPServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IotApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);

		try {
			UDPServer.run(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
