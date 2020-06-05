package com.lucky.shop.admin.mall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物车
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 12:55
 */
@Data
@TableName(value = "t_shop_cart")
public class ShopCart implements Serializable {
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
     * 数量
     */
    @TableField(value = "count")
    private String count;

    /**
     * 商品id
     */
    @TableField(value = "id_goods")
    private Long idGoods;

    /**
     * skuId
     */
    @TableField(value = "id_sku")
    private Long idSku;

    /**
     * 用户id
     */
    @TableField(value = "id_user")
    private Long idUser;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_COUNT = "count";

    public static final String COL_ID_GOODS = "id_goods";

    public static final String COL_ID_SKU = "id_sku";

    public static final String COL_ID_USER = "id_user";
}
