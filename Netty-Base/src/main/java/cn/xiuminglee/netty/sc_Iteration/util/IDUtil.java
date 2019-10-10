package cn.xiuminglee.netty.sc_Iteration.util;

import java.util.UUID;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/9/29 8:56
 * @Version 1.0
 * @Describe:
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
