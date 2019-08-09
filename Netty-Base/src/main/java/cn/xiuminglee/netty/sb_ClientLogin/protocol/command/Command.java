package cn.xiuminglee.netty.sb_ClientLogin.protocol.command;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 15:23
 * @Version 1.0
 * @Describe: 指令
 */
public interface Command {
    // 登录请求
    Byte LOGIN_REQUEST = 1;
    // 登录相应
    Byte LOGIN_RESPONSE = 2;
}
