package com.clh.iot.networkService;
import com.clh.iot.networkService.task.TcpTask;
import com.clh.iot.networkService.task.UdpTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class IotApplication implements CommandLineRunner {
	@Autowired
	private TcpTask tcpTask;
	@Autowired
	private UdpTask	udpTask;

	@Override
	public void run(String... args) throws Exception {

	}

	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);

	}

}
