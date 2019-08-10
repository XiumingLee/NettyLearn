package cn.xiuminglee.netty.sc_Iteration.client.handler;

import cn.xiuminglee.netty.sc_Iteration.protocol.packet.LoginRequestPacket;
import cn.xiuminglee.netty.sc_Iteration.protocol.packet.LoginResponsePacket;
import cn.xiuminglee.netty.sc_Iteration.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/10 13:48
 * @Version 1.0
 * @Describe:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("XiuMing");
        loginRequestPacket.setPassword("123456");
        System.out.println("channel--登录-》" + ctx.channel());
        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println(LocalDateTime.now() + ": 客户端登录成功");
            // 记录已登录标志
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(LocalDateTime.now() + ": 客户端登录失败，原因：" + msg.getReason());
        }
    }
}
