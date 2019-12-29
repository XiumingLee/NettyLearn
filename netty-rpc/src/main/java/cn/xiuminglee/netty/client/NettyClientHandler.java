package cn.xiuminglee.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @Author Xiuming Lee
 * @Description
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;//上下文
    private String result; //返回的结果
    private String para; //客户端调用方法时，传入的参数


    //与服务器的连接创建后，就会被调用, 这个方法是第一个被调用(1)
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" channelActive 被调用  ");
        context = ctx; //因为我们在其它方法(call方法)会使用到 ctx
    }

    //收到服务器的数据后，调用方法 (4)
    // 注意这里加了synchronized关键字，和下面的call方法保持同步。
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(" channelRead 被调用  ");
        result = msg.toString(); // 服务提供者将结果返回。
        notify(); // 唤醒等待的线程(唤醒call等待的线程。)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }



    // 注意这里加了synchronized关键字，和上面的的channelRead方法保持同步。
    @Override
    public synchronized Object call() throws Exception {
        System.out.println(" call1 被调用  ");
        context.writeAndFlush(para); // 将数据写出，这里也就是远程调用服务提供者的方法。
        //进行wait
        wait(); //等待channelRead 方法获取到服务器的结果后，唤醒
        System.out.println(" call2 被调用  ");
        return  result; //服务方返回的结果
    }

    // (2)
    public void setPara(String para) {
        System.out.println(" setPara  ");
        this.para = para;
    }
}
