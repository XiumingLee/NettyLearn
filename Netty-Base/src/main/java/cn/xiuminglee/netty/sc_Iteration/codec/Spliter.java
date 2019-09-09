package cn.xiuminglee.netty.sc_Iteration.codec;

import cn.xiuminglee.netty.sc_Iteration.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/12 19:21
 * @Version 1.0
 * @Describe: 用于拒绝非本协议连接
 */
public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close(); // 不符合我们定义的协议格式，关闭连接。
            return null;
        }
        return super.decode(ctx, in);
    }
}
