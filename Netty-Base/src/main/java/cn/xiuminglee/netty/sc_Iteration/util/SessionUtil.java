package cn.xiuminglee.netty.sc_Iteration.util;

import cn.xiuminglee.netty.sc_Iteration.attribute.Attributes;
import cn.xiuminglee.netty.sc_Iteration.session.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 16:44
 * @Version 1.0
 * @Describe: 登录工具类
 */
public class SessionUtil {
    // userId -> channel 的映射
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    /** 会话与Channel绑定*/
    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        // ***重点理解  --我的理解就是给Channel添加有个属性并赋值。
        channel.attr(Attributes.SESSION).set(session);
    }

    /** 会话与Channel解绑*/
    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            // ***重点理解 -- 我的理解：将属性的值设为 null
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    /** 判断是否登录*/
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    /** 根据Channel获取session*/
    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    /** 根据用户id获取Channel*/
    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }
}
