package com.lucky.shop.admin.cms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:04
 */
@Data
@TableName(value = "t_cms_article")
public class CmsArticle implements Serializable {
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
     * 作者
     */
    @TableField(value = "author")
    private String author;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 栏目id
     */
    @TableField(value = "id_channel")
    private Long idChannel;

    /**
     * 文章题图ID
     */
    @TableField(value = "img")
    private String img;

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

    public static final String COL_AUTHOR = "author";

    public static final String COL_CONTENT = "content";

    public static final String COL_ID_CHANNEL = "id_channel";

    public static final String COL_IMG = "img";

    public static final String COL_TITLE = "title";
}
