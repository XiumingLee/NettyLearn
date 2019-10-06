package cn.xiuminglee.netty.sc_Iteration.protocol.packet;

import cn.xiuminglee.netty.sc_Iteration.protocol.Packet;
import cn.xiuminglee.netty.sc_Iteration.protocol.command.Command;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/10/6 16:11
 * @Version 1.0
 * @Describe:
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
