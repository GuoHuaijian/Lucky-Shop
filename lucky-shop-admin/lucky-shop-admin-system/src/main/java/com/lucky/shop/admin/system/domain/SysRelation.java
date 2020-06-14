package com.lucky.shop.admin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单角色关系
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 22:50
 */
@Data
@TableName(value = "t_sys_relation")
public class SysRelation implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "menuid")
    private Long menuid;

    @TableField(value = "roleid")
    private Long roleid;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_MENUID = "menuid";

    public static final String COL_ROLEID = "roleid";
}
