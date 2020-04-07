package com.clh.iot.netty.packloss;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.Random;


public class UDPServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        String value="";
        try{
            ByteBuf byteBuf = packet.content();
            //上面说了,通过content()来获取消息内容
            int length = byteBuf.readableBytes();
            byte[] array = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), array);  //客户端透传 二进制 ，即计算机补码形式  :如AF =>1010 1111(补码)=>1101 0001(原码)  -81
            //模拟服务端读取IO 用时
            Random ra =new Random();

            Thread.sleep(ra.nextInt(10));
            String responseStr = new String(array,"ascii");  //ascii码表示

            System.out.println("UDP-Server recevice And sendBack:["+responseStr+"]");

            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(responseStr, CharsetUtil.US_ASCII), packet.sender()));

        }catch (Exception e){


        } finally {

        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        //防止服务关闭
        //ctx.close();
        cause.printStackTrace();
    }


}
