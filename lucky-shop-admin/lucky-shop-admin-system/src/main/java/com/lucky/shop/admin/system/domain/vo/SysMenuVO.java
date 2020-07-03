package com.lucky.shop.admin.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Guo Huaijian
 * @Date 2020/7/3 15:17
 */
@Data
public class SysMenuVO implements Serializable {

    private Long id;
    private String icon;
    private Long parentId;
    private String name;
    private String url;
    private Long levels;
    private Long ismenu;
    private Integer num;
    private String code;
    private Long status;
    private String component;
    private Long hidden;
}
