package com.clh.iot.networkService.netty.capacity;

import com.clh.iot.networkService.config.Const;
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
import io.netty.handler.codec.string.StringDecoder;
import org.springframework.stereotype.Component;

@Component
public class ControlBusServer {
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

                            ByteBuf delimiter = Unpooled.copiedBuffer("&".getBytes());
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,false, delimiter));
//                            ch.pipeline().addLast(new FixedLengthFrameDecoder(300));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new ControlBusServerHandler());
                        }
                    });

            System.out.println("Echo 服务器启动");
            ChannelFuture channelFuture =  serverBootstrap.bind(Const.TCP_SERVER_PORT).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws Exception {
        ControlBusServer controlBusServer = new ControlBusServer();
        controlBusServer.run();;
    }

}
