package cn.xiuminglee.netty.sc_Iteration.server;

import cn.xiuminglee.netty.sc_Iteration.codec.PacketCodecHandler;
import cn.xiuminglee.netty.sc_Iteration.codec.Spliter;
import cn.xiuminglee.netty.sc_Iteration.handle.IMIdleStateHandler;
import cn.xiuminglee.netty.sc_Iteration.server.handler.AuthHandler;
import cn.xiuminglee.netty.sc_Iteration.server.handler.HeartBeatRequestHandler;
import cn.xiuminglee.netty.sc_Iteration.server.handler.IMHandler;
import cn.xiuminglee.netty.sc_Iteration.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.time.LocalDateTime;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 14:53
 * @Version 1.0
 * @Describe: Netty服务端
 */
public class NettyServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        // 加在这里
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(IMHandler.INSTANCE);;
                    }
                });


        bind(serverBootstrap, PORT);
    }



    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(LocalDateTime.now() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
