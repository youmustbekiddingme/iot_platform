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

        for(int i=0;i<Const.UDP_PACKAGE_NUMS;i++){
            UDPClient udpClient = new UDPClient();
            udpClient.sendMessage(10000,i+"");
        }


        Thread.sleep(5000);
        //10s后主动关闭TCP连接
        ctx.close();


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
        //System.out.println("ControllerBusClientHandler invoke");
        super.channelInactive(ctx);
        //计算数据
        ClhUtils clhUtils  = new ClhUtils();
        Properties properties = clhUtils.loadProperties(Const.DEVICE_PATH);
        int udpPackNums=  properties.size();
       //  Map delayTimeMap = new HashMap();
        double delayTimeAll=0;
        for (String key : properties.stringPropertyNames()) {
            String val= properties.getProperty(key);
            String times[]=val.split(",");
            Long time1=Long.valueOf(times[0]);
            Long time2=Long.valueOf(times[1]);
            Long delayTimeOne=time2-time1;
            //delayTimeMap.put(key,delayTimeOne);
            delayTimeAll=delayTimeAll+delayTimeOne;
        }
        //System.out.println(delayTimeMap);
        System.out.println("udp发送报文次数："+ properties.size()+"次");
        System.out.println("总时延："+ delayTimeAll+"ms");
        System.out.println("平均时延"+delayTimeAll/udpPackNums+"ms");
        System.out.println("丢包率："+ ClhUtils.PercentNums  (1-properties.size()/Const.UDP_PACKAGE_NUMS));
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
