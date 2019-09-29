package cn.xiuminglee.netty.sc_Iteration.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/9/28 10:15
 * @Version 1.0
 * @Describe:
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
