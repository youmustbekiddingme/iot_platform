package com.clh.iot.networkService.netty.packloss;


import com.clh.iot.networkService.config.Const;
import com.clh.iot.networkService.netty.data.GenerateData;
import com.clh.iot.networkService.util.ClhUtils;
import com.google.gson.Gson;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

@Component
public class UDPClient {
    private static final Logger logger =
            LoggerFactory.getLogger("udplog");
    /**
     * 发送消息
     * @throws Exception
     */
    public   void sendMessage(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new UDPClientHandler()).remoteAddress(new InetSocketAddress(Const.UDP_SERVER_IP, Const.UDP_SERVER_PORT));

            ChannelFuture cf = bootstrap.connect();

            testNetwork(cf);

            //阻塞保持链路，除非主动关闭
            try {
                cf.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        finally {
            group.shutdownGracefully();
        }
    }

    /**
     * 测试网络
     * @param cf
     */
    private void testNetwork(ChannelFuture cf){
        ClhUtils clhUtils= new ClhUtils();
        String deviceId=Const.deviceId+"_";
        String key;
        String type="1_";
        String playLoad= GenerateData.prepareUdpPlayLoad(1000);

        for(int nums=1;nums<=Const.UDP_PACKAGE_NUMS;nums++){
            key=type+ deviceId+ nums;
            Long startTime = System.currentTimeMillis();
            String message=key+"-"+playLoad+","+startTime;  //1_A077468_1-1586228905993
            cf.channel().writeAndFlush(
                    new DatagramPacket(
                            Unpooled.copiedBuffer(message, CharsetUtil.US_ASCII),
                            new InetSocketAddress(
                                    Const.UDP_SERVER_IP,Const.UDP_SERVER_PORT
                            ))
            );


            while(true){
                try {
                    Thread.sleep(Const.UDP_HEART_BEAT);//200
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Properties properties = clhUtils.loadProperties(Const.DEVICE_PATH);
                if(properties.get(key)!=null){
                    //  logger.info("UDP-Client send-back success:"+key+":" +properties.get(key));
                    System.out.println("UDP-Client send-back success:"+key+":" +properties.get(key));
                    break;
                }
                if(System.currentTimeMillis()-startTime>Const.UDP_SEND_REC_DELAY_TIME){  //3000
                    System.out.println("UDP-Client send-back failed:"+key);
                    break;
                }

            }

            if(nums==Const.UDP_PACKAGE_NUMS){
                calUdpResult(cf);
            }
        }

    }



    /**
     * 统计结果
     */
    private void calUdpResult(ChannelFuture cf){
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
        double size = properties.size();
        System.out.println("UDP总共发送报文次数："+ Const.UDP_PACKAGE_NUMS+"次");
        System.out.println("UDP成功发送报文次数："+ properties.size()+"次");
        System.out.println("总时延："+ delayTimeAll+"ms");
        System.out.println("平均时延"+delayTimeAll/udpPackNums+"ms");
        System.out.println("丢包率："+ ClhUtils.PercentNums  (1-size/Const.UDP_PACKAGE_NUMS));


        Map mapRes = new HashMap();
        mapRes.put("from","UDP-CLIENT");
        mapRes.put("packSendAllNums",Const.UDP_PACKAGE_NUMS);
        mapRes.put("packSuccessNums",udpPackNums);
        mapRes.put("delayAllTime",delayTimeAll+"ms");
        mapRes.put("delayAvgTime",udpPackNums+"ms");
        mapRes.put("packLostRate",ClhUtils.PercentNums  (1-size/Const.UDP_PACKAGE_NUMS));

        Gson gson = new Gson();

        String strRes = gson.toJson(mapRes);
        strRes="2_"+strRes;
        //记录本地camera日志
        logger.info(strRes.substring(2,strRes.length()));

        //清空device文件
       clhUtils.clearProperties(Const.DEVICE_PATH);

       //上传结果到服务器
        uploadRes(cf,strRes);
    }

    /**
     * 上传结果
     * @param cf
     */
    private void uploadRes(ChannelFuture cf,String str){
        cf.channel().writeAndFlush(
                new DatagramPacket(
                        Unpooled.copiedBuffer(str, CharsetUtil.US_ASCII),
                        new InetSocketAddress(
                                Const.UDP_SERVER_IP,Const.UDP_SERVER_PORT
                        ))
        );
    }





    public static void main(String[] args) throws Exception{
        ClhUtils clhUtils = new ClhUtils();
        clhUtils.clearProperties(Const.DEVICE_PATH);
        Scanner sc = new Scanner(System.in);
        System.out.println("系统默认配置-UDP测试包数N："+Const.UDP_PACKAGE_NUMS+"，"+"UDP-SERVER模拟延迟时间(ms):"+Const.UDP_SERVER_DELAY_TIME
                +"，"+"UDP收发超时时间(ms):"+Const.UDP_SEND_REC_DELAY_TIME+ "，UDP-SERVER-IP:"+ Const.UDP_SERVER_IP+
                "，UDP-SERVER-PORT:"+Const.UDP_SERVER_PORT    + "，UDP-HEARTBEAT(ms):"+Const.UDP_HEART_BEAT+           "。"
        );

        System.out.println("是否采用默认系统配置？  1 YES,0 NO");
        int flag = sc.nextInt();
        if(flag==0){
            try {

                System.out.println("请输入UDP测试包数N：");
                int packNums = sc.nextInt();
                Const.UDP_PACKAGE_NUMS=packNums;

                System.out.println("请设定UDP丢包超时时间：");
                int udpSendRecDelayTime=sc.nextInt();
                Const.UDP_SEND_REC_DELAY_TIME=udpSendRecDelayTime;

                System.out.println("请设定UDP-SERVER-PORT");
                Const.UDP_SERVER_PORT=sc.nextInt();

                System.out.println("请设定UDP-SERVER-IP");
                Const.UDP_SERVER_IP=sc.next();

                System.out.println("请设定UDP_HEART_BEAT");
                Const.UDP_HEART_BEAT=sc.nextInt();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(flag==1){
            //采用系统默认配置
        }else{
            System.out.println("请输入有效的数字");
            return ;
        }


        UDPClient udpClient = new UDPClient();
        udpClient.sendMessage();


    }
}



