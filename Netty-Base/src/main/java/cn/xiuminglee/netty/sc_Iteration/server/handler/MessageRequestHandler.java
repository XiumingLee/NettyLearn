package cn.xiuminglee.netty.sc_Iteration.server.handler;

import cn.xiuminglee.netty.sc_Iteration.protocol.packet.MessageRequestPacket;
import cn.xiuminglee.netty.sc_Iteration.protocol.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/10 13:21
 * @Version 1.0
 * @Describe:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(LocalDateTime.now() + ": 收到客户端消息: " + msg.getMessage());
        messageResponsePacket.setMessage("服务端回复【" + msg.getMessage() + "】");

        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
