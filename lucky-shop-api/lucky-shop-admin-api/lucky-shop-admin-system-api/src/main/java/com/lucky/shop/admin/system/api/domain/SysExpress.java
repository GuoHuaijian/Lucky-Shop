package com.lucky.shop.admin.system.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 物流公司
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 19:02
 */
@Data
public class SysExpress implements Serializable {

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
     * 公司编码
     */
    private String code;

    /**
     * 是否禁用
     */
    private Boolean disabled;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_CODE = "code";

    public static final String COL_DISABLED = "disabled";

    public static final String COL_NAME = "name";

    public static final String COL_SORT = "sort";
}
