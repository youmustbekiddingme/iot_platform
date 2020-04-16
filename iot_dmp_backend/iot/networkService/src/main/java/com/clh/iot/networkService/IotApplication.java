package com.clh.iot.networkService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class IotApplication implements CommandLineRunner {

	@Override
	public void run(String... args)  {

	}

	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);

	}

}
