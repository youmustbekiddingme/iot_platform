package com.clh.iot.networkService;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.clh.iot.networkService.dao")

public class IotApplication implements CommandLineRunner {

	@Override
	public void run(String... args)  {

	}

	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);

	}

}
