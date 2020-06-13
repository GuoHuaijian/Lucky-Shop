package com.lucky.shop.admin.mall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 17:59
 */
@Data
@TableName(value = "t_shop_order_log")
public class ShopOrderLog implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间/注册时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @TableField(value = "modify_time")
    private Date modifyTime;

    /**
     * 日志详情
     */
    @TableField(value = "descript")
    private String descript;

    /**
     * 所属订单id
     */
    @TableField(value = "id_order")
    private Long idOrder;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_DESCRIPT = "descript";

    public static final String COL_ID_ORDER = "id_order";
}
