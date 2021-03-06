package cn.xiuminglee.netty.sb_ClientLogin.protocol.request;

import cn.xiuminglee.netty.sb_ClientLogin.protocol.Packet;
import cn.xiuminglee.netty.sb_ClientLogin.protocol.command.Command;
import lombok.Data;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 15:24
 * @Version 1.0
 * @Describe: 登录请求包
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
