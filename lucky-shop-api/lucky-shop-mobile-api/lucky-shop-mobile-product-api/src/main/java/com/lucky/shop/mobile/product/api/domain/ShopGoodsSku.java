package com.lucky.shop.mobile.product.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品SKu
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 14:28
 */
@Data
public class ShopGoodsSku implements Serializable {

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
     * sku编码,格式:逗号分割的属性值id
     */
    private String code;

    /**
     * sku名称,格式:逗号分割的属性值
     */
    private String codeName;

    /**
     * 商品id
     */
    private Long idGoods;

    /**
     * 市场价,原价
     */
    private String marketingPrice;

    /**
     * 价格
     */
    private String price;

    /**
     * 库存
     */
    private Integer stock;

    private static final long serialVersionUID = 1L;
}
