package com.clh.iot.networkService.netty.capacity;

import com.clh.iot.networkService.netty.data.GenerateData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.FileInputStream;
import java.io.InputStream;

public class ControlBusClientHandler  extends ChannelInboundHandlerAdapter {


    /**
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //准备1M 的测试数据
        byte byetes[]= GenerateData.prepareTcpBytes("A077468");

        ByteBuf mes;
        mes = Unpooled.buffer(byetes.length);
        mes.writeBytes(byetes);
        ctx.writeAndFlush(mes);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
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
        //client 需要buf 转换，服务端不需要
        ByteBuf buf = (ByteBuf)msg;
        byte[] barray = new byte[buf.readableBytes()];
        buf.getBytes(0,barray);
        String str=new String(barray);
        System.out.println("tcpclient-send-receive:"+str);
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
