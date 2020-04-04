package com.clh.iot.netty.packloss;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ControlBusClientHandler  extends ChannelInboundHandlerAdapter {

    /**
     * channel 激活时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String message = "Netty is a NIO client server framework which enables quick&_" +
                "and easy development of network applications&_ " +
                "such as protocol servers and clients.&_" +
                " It greatly simplifies and streamlines&_" +
                "network programming such as TCP and UDP socket server.&_";

        ByteBuf mes = null;
        mes = Unpooled.buffer(message.getBytes().length);
        mes.writeBytes(message.getBytes());
        ctx.writeAndFlush(mes);

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
        System.out.println("........hfahfhahfah");
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }


}
