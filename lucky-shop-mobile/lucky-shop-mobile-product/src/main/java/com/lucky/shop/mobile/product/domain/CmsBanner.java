package com.lucky.shop.mobile.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * banner管理
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 13:38
 */
@Data
@TableName(value = "t_cms_banner")
public class CmsBanner implements Serializable {
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
     * banner图id
     */
    @TableField(value = "id_file")
    private String idFile;

    /**
     * 界面
     */
    @TableField(value = "page")
    private String page;

    /**
     * 参数
     */
    @TableField(value = "param")
    private String param;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 点击banner跳转到url
     */
    @TableField(value = "url")
    private String url;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_ID_FILE = "id_file";

    public static final String COL_PAGE = "page";

    public static final String COL_PARAM = "param";

    public static final String COL_TITLE = "title";

    public static final String COL_TYPE = "type";

    public static final String COL_URL = "url";
}
