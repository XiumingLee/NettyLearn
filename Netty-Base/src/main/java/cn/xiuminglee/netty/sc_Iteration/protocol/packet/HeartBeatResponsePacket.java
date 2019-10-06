package cn.xiuminglee.netty.sc_Iteration.protocol.packet;

import cn.xiuminglee.netty.sc_Iteration.protocol.Packet;
import cn.xiuminglee.netty.sc_Iteration.protocol.command.Command;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/10/6 16:14
 * @Version 1.0
 * @Describe:
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
