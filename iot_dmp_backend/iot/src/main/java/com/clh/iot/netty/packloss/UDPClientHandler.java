package com.clh.iot.netty.packloss;

import com.clh.iot.netty.repo.UDPChannelRepo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;
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
        response=response+"-"+System.currentTimeMillis();
        System.out.println( "UDP-Client recevice:["+     response+"]");
    }






}