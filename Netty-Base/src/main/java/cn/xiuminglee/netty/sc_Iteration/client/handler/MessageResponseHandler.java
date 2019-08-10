package cn.xiuminglee.netty.sc_Iteration.client.handler;

import cn.xiuminglee.netty.sc_Iteration.protocol.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/10 13:47
 * @Version 1.0
 * @Describe:
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println(LocalDateTime.now() + ": 收到服务端的消息: " + msg.getMessage());
    }
}
