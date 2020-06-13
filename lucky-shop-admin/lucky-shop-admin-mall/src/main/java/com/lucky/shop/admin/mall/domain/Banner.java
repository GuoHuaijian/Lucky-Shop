package com.lucky.shop.admin.mall.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Banner
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 22:51
 */
@Data
public class Banner implements Serializable {

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
     * banner图id
     */
    private String idFile;

    /**
     * 界面
     */
    private String page;

    /**
     * 参数
     */
    private String param;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型
     */
    private String type;

    /**
     * 点击banner跳转到url
     */
    private String url;
}
