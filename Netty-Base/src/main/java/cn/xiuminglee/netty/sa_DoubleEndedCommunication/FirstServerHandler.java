package cn.xiuminglee.netty.sa_DoubleEndedCommunication;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/8 16:33
 * @Version 1.0
 * @Describe: 服务端逻辑处理器
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 收数据的逻辑
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(LocalDateTime.now() + ": 服务端读到客户端的数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

        // 回复数据到客户端
//        System.out.println(LocalDateTime.now() + ": 服务端写出数据");
//        ByteBuf out = getByteBuf(ctx);
//        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，欢迎访问我的个人博客网站->https://mrain22.cn/!".getBytes(Charset.forName("utf-8"));

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }
}
