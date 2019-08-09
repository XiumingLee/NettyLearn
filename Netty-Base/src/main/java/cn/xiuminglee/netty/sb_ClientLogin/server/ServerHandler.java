package cn.xiuminglee.netty.sb_ClientLogin.server;

import cn.xiuminglee.netty.sb_ClientLogin.protocol.Packet;
import cn.xiuminglee.netty.sb_ClientLogin.protocol.PacketCodeC;
import cn.xiuminglee.netty.sb_ClientLogin.protocol.request.LoginRequestPacket;
import cn.xiuminglee.netty.sb_ClientLogin.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 14:54
 * @Version 1.0
 * @Describe:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);
        // 判断是否是登录请求数据包
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            // 登录校验
            if (valid(loginRequestPacket)) {
                // 校验成功
                loginResponsePacket.setReason("账号密码校验成功");
                loginResponsePacket.setSuccess(true);
                System.out.println(LocalDateTime.now() + ": 登录成功!");
            } else {
                // 校验失败
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(LocalDateTime.now() + ": 登录失败!");
            }
            // 登录响应
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    /** 模拟用户名密码校验*/
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
