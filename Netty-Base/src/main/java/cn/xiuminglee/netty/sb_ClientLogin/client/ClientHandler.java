package cn.xiuminglee.netty.sb_ClientLogin.client;

import cn.xiuminglee.netty.sb_ClientLogin.protocol.Packet;
import cn.xiuminglee.netty.sb_ClientLogin.protocol.PacketCodeC;
import cn.xiuminglee.netty.sb_ClientLogin.protocol.request.LoginRequestPacket;
import cn.xiuminglee.netty.sb_ClientLogin.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 14:59
 * @Version 1.0
 * @Describe:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LocalDateTime.now() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("XiuMing");
        loginRequestPacket.setPassword("123456");

        // 编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                System.out.println(LocalDateTime.now() + ": 客户端登录成功");
            } else {
                System.out.println(LocalDateTime.now() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        }
    }
}
