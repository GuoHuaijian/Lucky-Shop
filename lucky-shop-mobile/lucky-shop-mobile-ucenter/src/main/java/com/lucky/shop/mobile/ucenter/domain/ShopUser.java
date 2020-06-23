package com.lucky.shop.mobile.ucenter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 0:46
 */
@Data
@TableName(value = "t_shop_user")
public class ShopUser implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 注册时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 性别:male;female
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 最后登陆时间
     */
    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 密码盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 微信头像
     */
    @TableField(value = "wechat_head_img_url")
    private String wechatHeadImgUrl;

    /**
     * 微信昵称
     */
    @TableField(value = "wechat_nick_name")
    private String wechatNickName;

    /**
     * 微信OpenID
     */
    @TableField(value = "wechat_open_id")
    private String wechatOpenId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_GENDER = "gender";

    public static final String COL_LAST_LOGIN_TIME = "last_login_time";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_NICK_NAME = "nick_name";

    public static final String COL_PASSWORD = "password";

    public static final String COL_SALT = "salt";

    public static final String COL_WECHAT_HEAD_IMG_URL = "wechat_head_img_url";

    public static final String COL_WECHAT_NICK_NAME = "wechat_nick_name";

    public static final String COL_WECHAT_OPEN_ID = "wechat_open_id";
}
