package com.clh.iot.network.netty.packloss;

import com.clh.iot.network.config.Const;
import com.clh.iot.network.util.ClhUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by IntelliJ IDEA 14.
 * User: karl.zhao
 * Time: 2016/1/21 0021.
 */
public class UDPClientHandler extends SimpleChannelInboundHandler<DatagramPacket>{

    /**
     * 接收到UDP SERVER 下发的数据
     * @param channelHandlerContext
     * @param datagramPacket
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        String response = datagramPacket.content().toString(CharsetUtil.US_ASCII);
        response=response+","+System.currentTimeMillis();   //01-1586228905993,1586228908030
        String key = response.substring(0,response.indexOf("-"));
        String value = response.substring(response.indexOf("-")+1,response.length());
        Map map  =  new HashMap();
        map.put(key,value);
        ClhUtils clhUtils = new ClhUtils();
        clhUtils.writeToProperties(Const.DEVICE_PATH,map);
        //主动断开连

        //channelHandlerContext.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);  //计算统计数据

    }
}