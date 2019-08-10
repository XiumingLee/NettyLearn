package cn.xiuminglee.netty.sc_Iteration.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/8/9 15:16
 * @Version 1.0
 * @Describe: 数据包接口
 */
@Data
public abstract class Packet {
    /** 协议版本 ：默认为 1 */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;


    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
