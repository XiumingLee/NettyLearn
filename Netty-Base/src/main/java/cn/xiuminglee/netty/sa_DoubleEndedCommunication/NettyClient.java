package cn.xiuminglee.netty.sa_DoubleEndedCommunication;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/8 15:39
 * @Version 1.0
 * @Describe:
 */
public class NettyClient {
    /** 最大尝试连接次数 */
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });
        // 4.建立连接
        connect(bootstrap,"127.0.0.1",8080,MAX_RETRY);
    }


    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接服务端成功!");
            } else if (retry == 0) {
                System.err.println("客户端重试连接次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(LocalDateTime.now() + ": 客户端连接失败，第" + order + "次重连……");
                // 定时任务，相隔指定时间再次尝试连接
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }
}
