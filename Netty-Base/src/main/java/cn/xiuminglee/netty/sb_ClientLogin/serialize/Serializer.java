package cn.xiuminglee.netty.sb_ClientLogin.serialize;


import cn.xiuminglee.netty.sb_ClientLogin.serialize.impl.JSONSerializer;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 15:30
 * @Version 1.0
 * @Describe: 自定义序列化接口
 */
public interface Serializer {

    Serializer DEFAULT = (Serializer) new JSONSerializer();
    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
