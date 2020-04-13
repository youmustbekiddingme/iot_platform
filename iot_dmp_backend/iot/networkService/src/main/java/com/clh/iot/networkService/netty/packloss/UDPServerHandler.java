package com.clh.iot.networkService.netty.packloss;

import com.clh.iot.networkService.config.Const;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;


public class UDPServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        String value;
        try{
            ByteBuf byteBuf = packet.content();
            int length = byteBuf.readableBytes();
            byte[] array = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), array);

            Thread.sleep(Const.UDP_SERVER_DELAY_TIME);
            String responseStr = new String(array,"ascii");

            System.out.println("UDP-Server recevice And sendBack:["+responseStr+"]");

            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(responseStr, CharsetUtil.US_ASCII), packet.sender()));

        }catch (Exception e){


        } finally {

        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        cause.printStackTrace();
    }


}
