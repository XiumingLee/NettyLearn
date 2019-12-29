package cn.xiuminglee.provider;

import cn.xiuminglee.netty.server.NettyServer;

/**
 * @Author Xiuming Lee
 * @Description 服务提供者
 * 会启动一个服务提供者，就是 NettyServer
 */
public class ProviderApplication {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 8000);
    }
}
