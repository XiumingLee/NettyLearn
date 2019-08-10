package cn.xiuminglee.netty.sc_Iteration.server.handler;

import cn.xiuminglee.netty.sc_Iteration.protocol.packet.LoginRequestPacket;
import cn.xiuminglee.netty.sc_Iteration.protocol.packet.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/10 13:15
 * @Version 1.0
 * @Describe: 登录请求处理器
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println(LocalDateTime.now() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(LocalDateTime.now() + ": 登录成功!");
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(LocalDateTime.now() + ": 登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }
    /** 模拟用户名密码校验*/
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
