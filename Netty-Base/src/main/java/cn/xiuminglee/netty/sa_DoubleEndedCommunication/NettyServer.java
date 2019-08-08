package cn.xiuminglee.netty.sa_DoubleEndedCommunication;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/8 14:40
 * @Version 1.0
 * @Describe: 服务端精简demo
 */
public class NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });

        bind(serverBootstrap,8080);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener((Future<? super Void> future)-> {
                if (future.isSuccess()) {
                    System.out.println("绑定端口[" + port + "]成功!");
                } else {
                    System.err.println("绑定端口[" + port + "]失败!");
                    bind(serverBootstrap, port + 1);
                }
        });
    }

}
