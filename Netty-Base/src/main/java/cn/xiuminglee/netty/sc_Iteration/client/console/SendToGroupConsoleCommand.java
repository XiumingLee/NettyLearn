package cn.xiuminglee.netty.sc_Iteration.client.console;

import cn.xiuminglee.netty.sc_Iteration.protocol.packet.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/9/29 15:56
 * @Version 1.0
 * @Describe:
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个群组：");

        String toGroupId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));

    }
}
