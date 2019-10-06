package cn.xiuminglee.netty.sc_Iteration.server.handler;

import cn.xiuminglee.netty.sc_Iteration.protocol.packet.HeartBeatRequestPacket;
import cn.xiuminglee.netty.sc_Iteration.protocol.packet.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/10/6 16:14
 * @Version 1.0
 * @Describe:
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
