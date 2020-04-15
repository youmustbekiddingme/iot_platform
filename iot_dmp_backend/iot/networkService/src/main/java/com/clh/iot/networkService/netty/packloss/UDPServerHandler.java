package com.clh.iot.networkService.netty.packloss;

import com.clh.iot.networkService.config.Const;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UDPServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private static final Logger logger = LoggerFactory.getLogger("udplog");
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        String value;
        try{
            ByteBuf byteBuf = packet.content();
            int length = byteBuf.readableBytes();
            byte[] array = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), array);
            //模拟休眠时间
//            Thread.sleep(Const.UDP_SERVER_DELAY_TIME);
            String responseStr = new String(array,"ascii");

            String type = responseStr.substring(0,1);
            if(type.equals("1")){
                //1    丢包测试
                ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(responseStr, CharsetUtil.US_ASCII), packet.sender()));
            }else if(type.equals("2")){
                //2 本地记录日志

                logger.info(responseStr.substring(2,responseStr.length()));
            }



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
