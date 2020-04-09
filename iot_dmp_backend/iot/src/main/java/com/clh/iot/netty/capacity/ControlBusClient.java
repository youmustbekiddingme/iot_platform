package com.clh.iot.netty.capacity;

import com.clh.iot.config.Const;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class ControlBusClient {
    private String host;
    private int port;

    public ControlBusClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)throws Exception {
                            ch.pipeline().addLast(new ControlBusClientHandler());
                        } });
            //连接到服务端，connect是异步链接，再调用同步方法sync，等待连接成功
            ChannelFuture f = bootstrap.connect().sync();

            //阻塞直到客户端通道关闭。不主动关闭，一直建立连接
            f.channel().closeFuture().sync();
        } finally {
            //优雅退出，释放NIO线程组
            group.shutdownGracefully().sync();
        }
    }


    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);


        try {
            System.out.println("请输入UDP测试包数");
            int nums = sc.nextInt();
            Const.UDP_PACKAGE_NUMS=nums;
            System.out.println("请输入TCP链路维持时间(ms)");
            int millTime=sc.nextInt();
            Const.TCP_CHANNEL_KEEP_TIME=millTime;
        } catch (Exception e) {
            System.out.println("数据数据异常");
        }
        new ControlBusClient(Const.TCP_SERVER_IP, Const.TCP_SERVER_PORT).start();

    }
}