package cn.xiuminglee.netty.sc_Iteration.protocol.packet;

import cn.xiuminglee.netty.sc_Iteration.protocol.Packet;
import cn.xiuminglee.netty.sc_Iteration.protocol.command.Command;
import lombok.Data;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 16:32
 * @Version 1.0
 * @Describe: 消息包
 */
@Data
public class MessageRequestPacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
