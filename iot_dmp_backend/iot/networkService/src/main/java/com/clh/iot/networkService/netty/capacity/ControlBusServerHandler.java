package com.clh.iot.networkService.netty.capacity;
import com.clh.iot.networkService.config.Const;
import com.clh.iot.networkService.util.ClhUtils;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ControlBusServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;
    Long startTime;
    String deviceId="";
    private static final Logger logger = LoggerFactory.getLogger("tcplog");

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("ControlBusServerHanler accept 1 ControlBusClient Close......");
    }

    /**
     * 服务端从客户端读取数据时触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String body = (String) msg;
        //   !A077468&

        if(body.contains("!")){
            deviceId=body.substring(1,body.length()-1);
            System.out.println("Begin Download.....");
            startTime=System.currentTimeMillis();
        }
        if(body.contains("#")){
           Long   endTime=System.currentTimeMillis();
            Long costTime = endTime-startTime;
            Map mapRes = new HashMap();
            double bandWith= Const.TCP_PACK_SIZE*1000.0/costTime ;
            mapRes.put("costTime",costTime);
            mapRes.put("deviceId",deviceId);
            mapRes.put("bandWith", ClhUtils.remainDecimal(bandWith,2)+"MB/S");
            Gson gson = new Gson();
            String res = gson.toJson(mapRes);
            logger.info(res);
           ByteBuf resp=  Unpooled.copiedBuffer(res.getBytes());
            ctx.writeAndFlush(resp);
        }
     //  System.out.println("服务端收到消息内容为：" + body + ", 收到消息次数：" + ++counter);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.printf("有一个TCP断开连接");
        ctx.close();
    }
}
