package com.lucky.shop.admin.seo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 专题
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 19:39
 */
@Data
@TableName(value = "t_promotion_topic")
public class PromotionTopic implements Serializable {
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
     * 是否禁用
     */
    @TableField(value = "disabled")
    private boolean disabled;

    /**
     * 专题文章
     */
    @TableField(value = "id_article")
    private Long idArticle;

    /**
     * 商品id列表
     */
    @TableField(value = "id_goods_list")
    private String idGoodsList;

    /**
     * 阅读量
     */
    @TableField(value = "pv")
    private Long pv;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_DISABLED = "disabled";

    public static final String COL_ID_ARTICLE = "id_article";

    public static final String COL_ID_GOODS_LIST = "id_goods_list";

    public static final String COL_PV = "pv";

    public static final String COL_TITLE = "title";
}
