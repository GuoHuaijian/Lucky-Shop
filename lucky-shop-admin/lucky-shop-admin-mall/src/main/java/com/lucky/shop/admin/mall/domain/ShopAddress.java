package com.lucky.shop.admin.mall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收获地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 19:36
 */
@Data
@TableName(value = "t_shop_address")
public class ShopAddress implements Serializable {
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
     * 详细地址
     */
    @TableField(value = "address_detail")
    private String addressDetail;

    /**
     * 地区编码
     */
    @TableField(value = "area_code")
    private String areaCode;

    /**
     * 市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 区县
     */
    @TableField(value = "district")
    private String district;

    /**
     * 用户id
     */
    @TableField(value = "id_user")
    private Long idUser;

    /**
     * 是否默认
     */
    @TableField(value = "is_default")
    private Byte isDefault;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Byte isDelete;

    /**
     * 收件人
     */
    @TableField(value = "name")
    private String name;

    /**
     * 邮政编码
     */
    @TableField(value = "post_code")
    private String postCode;

    /**
     * 省
     */
    @TableField(value = "province")
    private String province;

    /**
     * 联系电话
     */
    @TableField(value = "tel")
    private String tel;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_ADDRESS_DETAIL = "address_detail";

    public static final String COL_AREA_CODE = "area_code";

    public static final String COL_CITY = "city";

    public static final String COL_DISTRICT = "district";

    public static final String COL_ID_USER = "id_user";

    public static final String COL_IS_DEFAULT = "is_default";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_NAME = "name";

    public static final String COL_POST_CODE = "post_code";

    public static final String COL_PROVINCE = "province";

    public static final String COL_TEL = "tel";
}
