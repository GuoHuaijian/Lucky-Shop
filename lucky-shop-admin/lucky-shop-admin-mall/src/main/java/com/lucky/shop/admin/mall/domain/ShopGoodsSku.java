package com.lucky.shop.admin.mall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品SKU
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 21:18
 */
@Data
@TableName(value = "t_shop_goods_sku")
public class ShopGoodsSku implements Serializable {
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
     * sku编码,格式:逗号分割的属性值id
     */
    @TableField(value = "code")
    private String code;

    /**
     * sku名称,格式:逗号分割的属性值
     */
    @TableField(value = "code_name")
    private String codeName;

    /**
     * 商品id
     */
    @TableField(value = "id_goods")
    private Long idGoods;

    /**
     * 是否删除1:是,0:否
     */
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

    /**
     * 市场价,原价
     */
    @TableField(value = "marketing_price")
    private String marketingPrice;

    /**
     * 价格
     */
    @TableField(value = "price")
    private String price;

    /**
     * 库存
     */
    @TableField(value = "stock")
    private Integer stock;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_CODE = "code";

    public static final String COL_CODE_NAME = "code_name";

    public static final String COL_ID_GOODS = "id_goods";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_MARKETING_PRICE = "marketing_price";

    public static final String COL_PRICE = "price";

    public static final String COL_STOCK = "stock";
}
