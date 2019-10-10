package cn.xiuminglee.netty.sc_Iteration.protocol.packet;

import cn.xiuminglee.netty.sc_Iteration.protocol.Packet;
import cn.xiuminglee.netty.sc_Iteration.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/9/28 10:20
 * @Version 1.0
 * @Describe:
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
