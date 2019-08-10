package cn.xiuminglee.netty.sc_Iteration.protocol.command;

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

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
