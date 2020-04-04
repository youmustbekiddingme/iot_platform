package com.clh.iot.netty.packloss;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
public class ControlBusServer {
    private int port;

    public ControlBusServer(int port){
        this.port = port;
    }

    public void run() throws Exception{

        //配置服务端的线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)

                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ByteBuf delimiter = Unpooled.copiedBuffer("&_".getBytes());
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,false, delimiter));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new ControlBusServerHandler());
                        }
                    });

            System.out.println("Echo 服务器启动");
            //绑定端口，同步等待成功
            ChannelFuture channelFuture =  serverBootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();

        }finally {
            //优雅退出，释放线程池
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws Exception {
        int port = 8080;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new ControlBusServer(port).run();
    }

}
