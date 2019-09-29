package cn.xiuminglee.netty.sc_Iteration.server.handler;

import cn.xiuminglee.netty.sc_Iteration.protocol.packet.GroupMessageRequestPacket;
import cn.xiuminglee.netty.sc_Iteration.protocol.packet.GroupMessageResponsePacket;
import cn.xiuminglee.netty.sc_Iteration.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/9/29 15:46
 * @Version 1.0
 * @Describe:
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) {
        // 1.拿到 groupId 构造群聊消息的响应
        String groupId = requestPacket.getToGroupId();
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setMessage(requestPacket.getMessage());
        responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));


        // 2. 拿到群聊对应的 channelGroup，写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePacket);
    }
}
