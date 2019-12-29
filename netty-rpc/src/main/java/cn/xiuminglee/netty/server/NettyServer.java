package cn.xiuminglee.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author Xiuming Lee
 * @Description
 */
public class NettyServer {

    public static void startServer(String hostName, int port) {
        startServer0(hostName,port);
    }

    /** 编写一个方法，完成对NettyServer的初始化和启动 */
    private static void startServer0(String hostname, int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new StringDecoder()); // 解码：这里的编解码使用简单的字符创编解码。
                            pipeline.addLast(new StringEncoder()); // 编码
                            pipeline.addLast(new NettyServerHandler()); // 加入处理逻辑的Handler
                        }
                    });

            // 异步启动
            ChannelFuture sync = serverBootstrap.bind(hostname, port).sync();
            System.out.println("服务提供方开始提供服务~~");
            // 关闭channel时也采用异步的方式。
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭；
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
