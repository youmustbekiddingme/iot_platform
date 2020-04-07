package com.clh.iot.netty.packloss;

import com.clh.iot.config.Const;
import com.clh.iot.util.ClhUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Properties;

public class ControlBusClientHandler  extends ChannelInboundHandlerAdapter {

    /**
     * channel 激活时触发
     * TCP连接建立，此时触发10 秒100个udp报文
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //1.TCP连接建立
        // 2.读取设备名
        String deviceId= "A077468";
        //3.创建文件A077468.properties,新增100 帧序号




//        for(int i=0;i<1000;i++){
//            UDPClient.sendMessage(10000);
//            System.out.println("i="+i);
//        }


        {
            //启动UDP链路并发送1个报文
            UDPClient.sendMessage(10000,"01");
        }



            //计算时间、参数

            //计算完 清空UDP MAP


            //主动关闭TCP连接
          //ctx.close();





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
        //断开连接
        //删除local 文件
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
