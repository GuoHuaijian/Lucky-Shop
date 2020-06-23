package com.lucky.shop.mobile.ucenter.api.domain;

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
public class ShopUser implements Serializable {

    private Long id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 性别:male;female
     */
    private String gender;

    /**
     * 最后登陆时间
     */
    private Date lastLoginTime;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 微信头像
     */
    private String wechatHeadImgUrl;

    /**
     * 微信昵称
     */
    private String wechatNickName;

    /**
     * 微信OpenID
     */
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
