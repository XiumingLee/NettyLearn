package cn.xiuminglee.netty.sc_Iteration.protocol.packet;

import cn.xiuminglee.netty.sc_Iteration.protocol.Packet;
import cn.xiuminglee.netty.sc_Iteration.protocol.command.Command;
import cn.xiuminglee.netty.sc_Iteration.session.Session;
import lombok.Data;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/9/29 15:49
 * @Version 1.0
 * @Describe:
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
