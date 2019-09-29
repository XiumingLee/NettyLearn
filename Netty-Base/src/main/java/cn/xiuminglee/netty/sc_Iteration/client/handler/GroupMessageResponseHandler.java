package cn.xiuminglee.netty.sc_Iteration.client.handler;

import cn.xiuminglee.netty.sc_Iteration.protocol.packet.GroupMessageResponsePacket;
import cn.xiuminglee.netty.sc_Iteration.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/9/29 15:53
 * @Version 1.0
 * @Describe:
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket) {
        String fromGroupId = responsePacket.getFromGroupId();
        Session fromUser = responsePacket.getFromUser();
        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + responsePacket.getMessage());
    }
}