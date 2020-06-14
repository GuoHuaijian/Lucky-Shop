package com.lucky.shop.admin.system.domain.vo;

import com.lucky.shop.common.core.utils.Lists;
import lombok.Data;

import java.util.List;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/14 20:03
 */
@Data
public class RouterMenu {
    private Long id;
    private Long parentId;
    private String path;
    private String component;
    private String name;
    private Integer num;
    private Boolean hidden = false;
    private MenuMeta meta;
    private List<RouterMenu> children = Lists.newArrayList();

}
