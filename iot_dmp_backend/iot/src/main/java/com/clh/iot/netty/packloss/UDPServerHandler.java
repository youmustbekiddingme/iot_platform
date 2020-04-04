package com.clh.iot.netty.packloss;

import com.clh.iot.util.ClhUtils;
import com.clh.iot.util.DeviceUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
public class UDPServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    int a=1;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        String value="";
        try{
            ByteBuf byteBuf = packet.content();
            //上面说了,通过content()来获取消息内容
            int length = byteBuf.readableBytes();
            byte[] array = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), array);  //客户端透传 二进制 ，即计算机补码形式  :如AF =>1010 1111(补码)=>1101 0001(原码)  -81
            // 测试数据：01 01 01 07 01 58 58 17 85 89 70 51 97       51 低位 ，97高位。01 01 01 07 01 58 58 17 85 89 70=> 97 51,工具生成高位在左，低位在右
            Thread.sleep(10000);
            System.out.printf("");
            value= ClhUtils.Bytes2Hexstring(array);
            a=a++;
            int nums=0;
            System.out.println(value+"-times:"+a);
            byte bytes[]=ClhUtils.Hexstring2Bytes(value);

            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(bytes), packet.sender()));

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
