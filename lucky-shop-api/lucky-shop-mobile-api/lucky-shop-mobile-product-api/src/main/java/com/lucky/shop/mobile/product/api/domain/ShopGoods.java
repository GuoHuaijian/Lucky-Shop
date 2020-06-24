package com.lucky.shop.mobile.product.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 14:40
 */
@Data
public class ShopGoods implements Serializable {

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
     * 产品简介
     */
    private String descript;

    /**
     * 产品详情
     */
    private String detail;

    /**
     * 大图相册列表,以逗号分隔
     */
    private String gallery;

    /**
     * 类别id
     */
    private Long idCategory;

    /**
     * 是否删除
     */
    private Byte isDelete;

    /**
     * 是否人气商品
     */
    private Byte isHot;

    /**
     * 是否新品推荐
     */
    private Byte isNew;

    /**
     * 是否上架
     */
    private Byte isOnSale;

    /**
     * 收藏数
     */
    private Integer likeNum;

    /**
     * 名称
     */
    private String name;

    /**
     * 小图
     */
    private String pic;

    /**
     * 价格
     */
    private String price;

    /**
     * 库存数量
     */
    private Integer stock;

    private static final long serialVersionUID = 1L;
}
