package cn.xiuminglee.netty.sc_Iteration.codec;

import cn.xiuminglee.netty.sc_Iteration.protocol.Packet;
import cn.xiuminglee.netty.sc_Iteration.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/10 13:32
 * @Version 1.0
 * @Describe: 编码
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out, msg);
    }
}
