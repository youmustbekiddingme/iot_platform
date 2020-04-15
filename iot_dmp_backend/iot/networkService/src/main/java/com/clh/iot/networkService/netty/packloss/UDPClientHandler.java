package com.clh.iot.networkService.netty.packloss;

import com.clh.iot.networkService.config.Const;
import com.clh.iot.networkService.util.ClhUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

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
        response=response+","+System.currentTimeMillis();   //1_A077468_1-1586228905993,1586228908030
        String key = response.substring(0,response.indexOf("-"));
        String value = response.substring(response.indexOf("-")+1,response.length());
        String []val=value.split(",");
        String value2= val[1]+","+val[2];
        Map map  =  new HashMap();
        map.put(key,value2);
        ClhUtils clhUtils = new ClhUtils();
        clhUtils.writeToProperties(Const.DEVICE_PATH,map);
        //channelHandlerContext.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}