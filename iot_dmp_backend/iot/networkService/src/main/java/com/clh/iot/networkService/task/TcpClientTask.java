package com.clh.iot.networkService.task;

import com.clh.iot.networkService.netty.capacity.ControlBusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
@Component
public class TcpClientTask implements Callable {
    @Autowired
    private ControlBusClient controlBusClient;
    @Override
    public Object call() throws Exception {
        controlBusClient.start();
        return null;
    }
}
