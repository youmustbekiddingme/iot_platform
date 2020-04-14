package com.clh.iot.networkService.task;

import com.clh.iot.networkService.netty.packloss.UDPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
@Component
public class UdpClientTask implements Callable {
    @Autowired
    private UDPClient udpClient;
    @Override
    public Object call() throws Exception {
        udpClient.sendMessage();
        return null;
    }
}
