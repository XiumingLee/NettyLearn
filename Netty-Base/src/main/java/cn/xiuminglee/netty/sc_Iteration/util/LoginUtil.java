package cn.xiuminglee.netty.sc_Iteration.util;

import cn.xiuminglee.netty.sc_Iteration.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 16:44
 * @Version 1.0
 * @Describe: 登录工具类
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
