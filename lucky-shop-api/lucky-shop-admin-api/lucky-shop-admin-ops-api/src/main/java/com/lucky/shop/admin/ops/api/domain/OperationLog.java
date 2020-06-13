package com.lucky.shop.admin.ops.api.domain;

import lombok.Data;

import java.util.Date;

/**
 * 操作日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 1:57
 */
@Data
public class OperationLog {

    private Long id;

    private String logtype;

    private String logname;

    private Integer userid;

    private String classname;

    private String method;

    private Date createTime;

    private String succeed;

    private String message;
}
