package cn.xiuminglee.netty.sc_Iteration.attribute;

import cn.xiuminglee.netty.sc_Iteration.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 16:40
 * @Version 1.0
 * @Describe: 标志属性枚举。
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
