package com.lucky.shop.admin.ops.api.domain;

import lombok.Data;

import java.util.Date;

/**
 * 登录日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 1:55
 */
@Data
public class LoginLog {

    private Integer id;

    private String logname;

    private Integer userid;

    private String succeed;

    private String message;

    private String ip;

    private Date createTime;
}
