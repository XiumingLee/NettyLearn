package cn.xiuminglee.netty.sc_Iteration.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/9/25 9:56
 * @Version 1.0
 * @Describe:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    /**用户唯一性标识 */
    private String userId;
    private String userName;
}
