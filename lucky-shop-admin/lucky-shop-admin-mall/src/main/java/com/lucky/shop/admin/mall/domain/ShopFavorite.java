package com.lucky.shop.admin.mall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户收藏
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 22:46
 */
@Data
@TableName(value = "t_shop_favorite")
public class ShopFavorite implements Serializable {
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
     * 商品id
     */
    @TableField(value = "id_goods")
    private Long idGoods;

    /**
     * 用户id
     */
    @TableField(value = "id_user")
    private Long idUser;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_ID_GOODS = "id_goods";

    public static final String COL_ID_USER = "id_user";
}
