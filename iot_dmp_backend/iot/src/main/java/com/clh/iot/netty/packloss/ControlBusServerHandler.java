package com.clh.iot.netty.packloss;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
public class ControlBusServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;


    /**
     * 服务端从客户端读取数据时触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String body = (String) msg;
        System.out.println("服务端收到消息内容为：" + body + ", 收到消息次数：" + ++counter);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
