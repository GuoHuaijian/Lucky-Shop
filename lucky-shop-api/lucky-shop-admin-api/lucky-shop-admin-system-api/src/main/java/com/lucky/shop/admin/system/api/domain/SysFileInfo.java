package com.lucky.shop.admin.system.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 19:03
 */
@Data
public class SysFileInfo implements Serializable {

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

    private String originalFileName;

    private String realFileName;

    private String ablatePath;
}
