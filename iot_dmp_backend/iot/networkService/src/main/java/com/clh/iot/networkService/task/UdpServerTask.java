package com.clh.iot.networkService.task;

import com.clh.iot.networkService.netty.packloss.UDPServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
@Component
public class UdpServerTask implements Callable {
    @Autowired
    private UDPServer udpServer;
    @Override
    public Object call() throws Exception {
        udpServer.run();
        return null;
    }
}
