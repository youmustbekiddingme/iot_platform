package com.clh.iot.networkService.task;

import com.clh.iot.networkService.netty.capacity.ControlBusServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class TcpServerTask implements Callable {
    @Autowired
    private ControlBusServer controlBusServer;

    @Override
    public Object call() throws Exception {
        controlBusServer.run();
        return null;
    }
}
