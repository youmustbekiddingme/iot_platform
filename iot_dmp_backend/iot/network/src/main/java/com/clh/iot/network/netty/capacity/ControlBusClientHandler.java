package com.clh.iot.network.netty.capacity;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.FileInputStream;
import java.io.InputStream;

public class ControlBusClientHandler  extends ChannelInboundHandlerAdapter {




    /**
     * channel 激活时触发
     * TCP连接建立，此时触发10 秒100个udp报文
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //向TCP Server 发送数据
//        String message = "Netty is a NIO client server framework which enables quick&_" +
//                "and easy development of network applications&_ " +
//                "such as protocol servers and clients.&_" +
//                " It greatly simplifies and streamlines&_" +
//                "network programming such as TCP and UDP socket server.&_";

        InputStream in=new FileInputStream("d:\\test.txt");
        //1.分配一块内存空间 临时的空间 存放我文件的数据
        byte[] b=new byte[in.available()];
        //2.将数据读入到内存空间
        in.read(b);

//        ByteBuf mes = null;
//        mes = Unpooled.buffer(message.getBytes().length);
//        mes.writeBytes(message.getBytes());
        ByteBuf mes = null;
        mes = Unpooled.buffer(b.length);
        mes.writeBytes(b);
        ctx.writeAndFlush(mes);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //断开连接
        //删除local 文件
        super.channelInactive(ctx);

//        //清空device文件
//        clhUtils.clearProperties(Const.DEVICE_PATH);
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
