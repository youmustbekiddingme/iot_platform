package com.clh.iot.networkService.netty.packloss;
import com.clh.iot.networkService.config.Const;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UDPServer {
    public  void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new UDPServerHandler());
        b.bind(Const.UDP_SERVER_PORT).sync().channel().closeFuture().await();
    }

    public static void main(String[] args)throws  Exception {

        UDPServer udpServer = new UDPServer();
        udpServer.run();;
    }
}
