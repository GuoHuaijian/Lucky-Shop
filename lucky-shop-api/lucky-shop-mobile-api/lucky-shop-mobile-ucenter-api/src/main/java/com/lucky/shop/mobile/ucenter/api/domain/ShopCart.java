package com.lucky.shop.mobile.ucenter.api.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:03
 */
@Data
public class ShopCart implements Serializable {

    private Long id;

    /**
     * 创建时间/注册时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date modifyTime;

    /**
     * 数量
     */
    private BigDecimal count;

    /**
     * 商品id
     */
    private Long idGoods;

    /**
     * skuId
     */
    private Long idSku;

    /**
     * 用户id
     */
    private Long idUser;

    @Transient
    @TableField(exist = false)
    private BigDecimal price;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_COUNT = "count";

    public static final String COL_ID_GOODS = "id_goods";

    public static final String COL_ID_SKU = "id_sku";

    public static final String COL_ID_USER = "id_user";
}
