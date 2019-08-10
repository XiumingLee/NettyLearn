package cn.xiuminglee.netty.sc_Iteration.protocol.packet;

import cn.xiuminglee.netty.sc_Iteration.protocol.Packet;
import cn.xiuminglee.netty.sc_Iteration.protocol.command.Command;
import lombok.Data;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 15:28
 * @Version 1.0
 * @Describe: 登录响应包
 */
@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
