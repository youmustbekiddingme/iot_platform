package com.clh.iot.netty.packloss;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ControlBusClientHandler  extends ChannelInboundHandlerAdapter {

    /**
     * channel 激活时触发
     * TCP连接建立，此时触发10 秒100个udp报文
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //TCP连接建立 ，发送100个报文
        Long timeBegin = System.currentTimeMillis();
//        for(int i=0;i<1000;i++){
//            UDPClient.sendMessage(10000);
//            System.out.println("i="+i);
//        }
            //启动UDP链路并发送数据。
            UDPClient.sendMessage(10000);


//        Long timeEnd = System.currentTimeMillis();
//        Long xxx= timeEnd - timeBegin;
//        System.out.println(xxx);

        //ctx.close();

        //判断逻辑



        //向TCP Server 发送数据
//        String message = "Netty is a NIO client server framework which enables quick&_" +
//                "and easy development of network applications&_ " +
//                "such as protocol servers and clients.&_" +
//                " It greatly simplifies and streamlines&_" +
//                "network programming such as TCP and UDP socket server.&_";
//
//        ByteBuf mes = null;
//        mes = Unpooled.buffer(message.getBytes().length);
//        mes.writeBytes(message.getBytes());
//        ctx.writeAndFlush(mes);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ControllerBusClientHandler invoke");
        super.channelInactive(ctx);
    }


    /**
     * 客户端从服务端读取数据的时候触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }




}
