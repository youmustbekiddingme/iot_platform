package com.clh.iot.netty.packloss;


import com.clh.iot.config.Const;
import com.clh.iot.util.ClhUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UDPClient {

    /**
     * 发送消息
     * @param port  端口号
     * @throws Exception
     */
    public   void sendMessage(int port)throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new UDPClientHandler()).remoteAddress(new InetSocketAddress(Const.UDP_SERVER_IP, port));


            // 同步等待成功连接
            ChannelFuture cf = bootstrap.connect();



            ClhUtils clhUtils= new ClhUtils();

            for(int nums=1;nums<=Const.UDP_PACKAGE_NUMS;nums++){

                Long startTime = System.currentTimeMillis();
                String message=nums+"-"+startTime;  //01-1586228905993
                //测试future 为异步
                cf.channel().writeAndFlush(
                        new DatagramPacket(
                                Unpooled.copiedBuffer(message, CharsetUtil.US_ASCII),
                                new InetSocketAddress(
                                        Const.UDP_SERVER_IP,port
                                )));


               // addStartTime(message);
                while(true){

                    Thread.sleep(200);
                    Properties properties = clhUtils.loadProperties(Const.DEVICE_PATH);

                    if(properties.get(nums+"")!=null){
                        System.out.println("UDP-Client send-back success:"+nums+":" +properties.get(nums+""));
                        break;
                    }
                    if(System.currentTimeMillis()-startTime>3000){
                        System.out.println("UDP-Client send-back failed:"+nums);
                        break;
                    }



                }

                if(nums==Const.UDP_PACKAGE_NUMS){
                    calUdpResult();
                }
            }


            //阻塞保持链路，除非主动关闭
            cf.channel().closeFuture().sync();



        }
        finally {
            group.shutdownGracefully();
        }
    }

    /**
     * 新增某一帧开始时间
     * @param message
     */
    private static void addStartTime(String message){
        String startTime = message.substring(message.indexOf("-")+1,message.length());
        ClhUtils clhUtils = new ClhUtils();
        Properties properties= clhUtils.loadProperties(Const.DEVICE_PATH);
        String key = message.substring(0,message.indexOf("-"));
        properties.put(key,startTime);
    }
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);


        try {
            System.out.println("请输入UDP测试包数");
            int nums = sc.nextInt();
            Const.UDP_PACKAGE_NUMS=nums;

        } catch (Exception e) {
            System.out.println("数据数据异常");
        }

        UDPClient udpClient = new UDPClient();
        udpClient.sendMessage(Const.UDP_SERVER_PORT);

    }

    /**
     * 统计结果
     */
    private void calUdpResult(){
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
            delayTimeAll=delayTimeAll+delayTimeOne;
        }

        System.out.println("UDP总共发送报文次数："+ Const.UDP_PACKAGE_NUMS+"次");
        System.out.println("UDP成功发送报文次数："+ properties.size()+"次");
        System.out.println("总时延："+ delayTimeAll+"ms");
        System.out.println("平均时延"+delayTimeAll/udpPackNums+"ms");
        System.out.println("丢包率："+ ClhUtils.PercentNums  (1-properties.size()/Const.UDP_PACKAGE_NUMS));

        //清空device文件
        clhUtils.clearProperties(Const.DEVICE_PATH);
    }
}



