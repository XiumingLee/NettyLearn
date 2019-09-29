package cn.xiuminglee.netty.sc_Iteration.client.handler;

import cn.xiuminglee.netty.sc_Iteration.protocol.packet.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/9/29 15:02
 * @Version 1.0
 * @Describe:
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket responsePacket) {
        System.out.println("群[" + responsePacket.getGroupId() + "]中的人包括：" + responsePacket.getSessionList());
    }
}
