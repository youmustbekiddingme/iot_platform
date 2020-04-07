package com.clh.iot.netty.packloss;


import com.clh.iot.config.Const;
import com.clh.iot.util.ClhUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UDPClient {


    /**
     * 发送消息
     * @param port  端口号
     * @throws Exception
     */
    public static void sendMessage(int port,String nums)throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new UDPClientHandler()).remoteAddress(new InetSocketAddress("localhost", port));


            // 同步等待成功连接
            ChannelFuture cf = bootstrap.connect().sync();
            Long startTime = System.currentTimeMillis();
            String message=nums+"-"+startTime;  //01-1586228905993
            //测试future 为异步
            Future future=  cf.channel().writeAndFlush(
                        new DatagramPacket(
                                Unpooled.copiedBuffer(message, CharsetUtil.US_ASCII),
                                new InetSocketAddress(
                                        "localhost",port
                                ))).sync();
            System.out.println( "UDP-Client send:["+   message  +"]");
            addStartTime(message);

            //阻塞保持链路，除非主动关闭
            cf.channel().closeFuture().sync();


            //异步调用
          //  f.sync();




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
        Properties properties= ClhUtils.loadProperties(Const.DEVICE_PATH);
        String key = message.substring(0,message.indexOf("-"));
        properties.put(key,startTime);
       // properties.getProperty(key);
    }
    public static void main(String[] args) throws Exception{
//        String xxx= "01-1586228905993";
//        String yyy=xxx.substring(xxx.indexOf("-")+1,xxx.length());
//        String zzz= xxx.substring(0,xxx.indexOf("-"));
//        System.out.println(zzz);

       // new UDPClient().sendMessage(10000,"01");

//        Properties properties= ClhUtils.loadProperties(Const.DEVICE_PATH);
//        properties.setProperty("01","qqqqz");
//        properties.setProperty("testSong","Someone Like You");
//        properties.setProperty("testQQ","只能说我认了,也许是你怕了");

//        Map map = new HashMap();
//        ClhUtils.writeToProperties(Const.DEVICE_PATH,map);
        String yyy="01-1586228905993,1586228908030";
        String key=yyy.substring(0,yyy.indexOf("-"));
        String value= yyy.substring(yyy.indexOf("-")+1,yyy.length());
        System.out.println(key);
        System.out.printf(value);

    }
}



