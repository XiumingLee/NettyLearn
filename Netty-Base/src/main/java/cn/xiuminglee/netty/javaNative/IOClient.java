package cn.xiuminglee.netty.javaNative;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/8 11:38
 * @Version 1.0
 * @Describe:
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((LocalDateTime.now() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }
}
