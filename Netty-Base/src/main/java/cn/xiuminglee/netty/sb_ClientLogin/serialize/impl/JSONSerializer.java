package cn.xiuminglee.netty.sb_ClientLogin.serialize.impl;

import cn.xiuminglee.netty.sb_ClientLogin.serialize.Serializer;
import cn.xiuminglee.netty.sb_ClientLogin.serialize.SerializerAlogrithm;
import com.alibaba.fastjson.JSON;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 15:31
 * @Version 1.0
 * @Describe:
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
