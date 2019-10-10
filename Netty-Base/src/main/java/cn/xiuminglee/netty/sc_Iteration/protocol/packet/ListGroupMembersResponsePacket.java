package cn.xiuminglee.netty.sc_Iteration.protocol.packet;

import cn.xiuminglee.netty.sc_Iteration.protocol.Packet;
import cn.xiuminglee.netty.sc_Iteration.protocol.command.Command;
import cn.xiuminglee.netty.sc_Iteration.session.Session;
import lombok.Data;

import java.util.List;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/9/29 14:57
 * @Version 1.0
 * @Describe:
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
