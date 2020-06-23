package com.lucky.shop.mobile.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:46
 */
@Data
@TableName(value = "t_shop_goods")
public class ShopGoods implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间/注册时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后更新人
     */
    @TableField(value = "modify_by")
    private Long modifyBy;

    /**
     * 最后更新时间
     */
    @TableField(value = "modify_time")
    private Date modifyTime;

    /**
     * 产品简介
     */
    @TableField(value = "descript")
    private String descript;

    /**
     * 产品详情
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 大图相册列表,以逗号分隔
     */
    @TableField(value = "gallery")
    private String gallery;

    /**
     * 类别id
     */
    @TableField(value = "id_category")
    private Long idCategory;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Byte isDelete;

    /**
     * 是否人气商品
     */
    @TableField(value = "is_hot")
    private Byte isHot;

    /**
     * 是否新品推荐
     */
    @TableField(value = "is_new")
    private Byte isNew;

    /**
     * 是否上架
     */
    @TableField(value = "is_on_sale")
    private Byte isOnSale;

    /**
     * 收藏数
     */
    @TableField(value = "like_num")
    private Integer likeNum;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 小图
     */
    @TableField(value = "pic")
    private String pic;

    /**
     * 价格
     */
    @TableField(value = "price")
    private String price;

    /**
     * 库存数量
     */
    @TableField(value = "stock")
    private Integer stock;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_DESCRIPT = "descript";

    public static final String COL_DETAIL = "detail";

    public static final String COL_GALLERY = "gallery";

    public static final String COL_ID_CATEGORY = "id_category";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_IS_HOT = "is_hot";

    public static final String COL_IS_NEW = "is_new";

    public static final String COL_IS_ON_SALE = "is_on_sale";

    public static final String COL_LIKE_NUM = "like_num";

    public static final String COL_NAME = "name";

    public static final String COL_PIC = "pic";

    public static final String COL_PRICE = "price";

    public static final String COL_STOCK = "stock";
}
