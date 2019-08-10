package cn.xiuminglee.netty.sc_Iteration.codec;

import cn.xiuminglee.netty.sc_Iteration.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/10 12:57
 * @Version 1.0
 * @Describe: 解码
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
