package com.clh.iot.netty.packloss;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;

import java.net.InetSocketAddress;

public class UDPClient {


    /**
     * 发送消息
     * @param port  端口号
     * @throws Exception
     */
    public static void sendMessage(int port)throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new UDPClientHandler()).remoteAddress(new InetSocketAddress("localhost", port));


            // 同步等待成功连接
            ChannelFuture cf = bootstrap.connect().sync();
            Long startTime = System.currentTimeMillis();
            String message="A077468-01"+"-"+startTime;
            //测试future 为异步
            Future future=  cf.channel().writeAndFlush(
                        new DatagramPacket(
                                Unpooled.copiedBuffer(message, CharsetUtil.US_ASCII),
                                new InetSocketAddress(
                                        "localhost",port
                                ))).sync();
            System.out.println( "UDP-Client send:["+   message  +"]");
            //阻塞保持链路，除非主动关闭
            cf.channel().closeFuture().sync();


            //异步调用
          //  f.sync();




        }
        finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{

        new UDPClient().sendMessage(10000);
    }
}



