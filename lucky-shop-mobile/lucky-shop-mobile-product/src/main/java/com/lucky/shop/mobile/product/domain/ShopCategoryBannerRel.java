package com.lucky.shop.mobile.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 类别banner关联表
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:27
 */
@Data
@TableName(value = "t_shop_category_banner_rel")
public class ShopCategoryBannerRel implements Serializable {
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
     * banner id
     */
    @TableField(value = "id_banner")
    private Long idBanner;

    /**
     * 类别id
     */
    @TableField(value = "id_category")
    private Long idCategory;

    @TableField(exist = false)
    private CmsBanner banner;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_ID_BANNER = "id_banner";

    public static final String COL_ID_CATEGORY = "id_category";
}
