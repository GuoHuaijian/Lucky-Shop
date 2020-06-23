package com.lucky.shop.mobile.order.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单明细
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:04
 */
@Data
@TableName(value = "t_shop_order_item")
public class ShopOrderItem implements Serializable {
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
    private BigDecimal count;

    /**
     * 商品id
     */
    @TableField(value = "id_goods")
    private Long idGoods;

    /**
     * 所属订单id
     */
    @TableField(value = "id_order")
    private Long idOrder;

    /**
     * skuId
     */
    @TableField(value = "id_sku")
    private Long idSku;

    /**
     * 单价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 合计
     */
    @TableField(value = "total_price")
    private BigDecimal totalPrice;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_COUNT = "count";

    public static final String COL_ID_GOODS = "id_goods";

    public static final String COL_ID_ORDER = "id_order";

    public static final String COL_ID_SKU = "id_sku";

    public static final String COL_PRICE = "price";

    public static final String COL_TOTAL_PRICE = "total_price";
}
