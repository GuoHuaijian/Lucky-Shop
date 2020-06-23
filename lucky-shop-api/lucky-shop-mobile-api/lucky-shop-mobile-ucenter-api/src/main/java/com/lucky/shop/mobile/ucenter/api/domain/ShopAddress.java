package com.lucky.shop.mobile.ucenter.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收货地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:40
 */
@Data
public class ShopAddress implements Serializable {

    private Long id;

    /**
     * 创建时间/注册时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date modifyTime;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 用户id
     */
    private Long idUser;

    /**
     * 是否默认
     */
    private Byte isDefault;

    /**
     * 是否删除
     */
    private Byte isDelete;

    /**
     * 收件人
     */
    private String name;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 省
     */
    private String province;

    /**
     * 联系电话
     */
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
