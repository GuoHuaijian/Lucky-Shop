package com.lucky.shop.admin.system.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统参数
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 12:39
 */
@Data
public class SysCfg implements Serializable {

    private Long id;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间/注册时间
     */
    private Date createTime;

    /**
     * 最后更新人
     */
    private Long modifyBy;

    /**
     * 最后更新时间
     */
    private Date modifyTime;

    /**
     * 备注
     */
    private String cfgDesc;

    /**
     * 参数名
     */
    private String cfgName;

    /**
     * 参数值
     */
    private String cfgValue;
}
