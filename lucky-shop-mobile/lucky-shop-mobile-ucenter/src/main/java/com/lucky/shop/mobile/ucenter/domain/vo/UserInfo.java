package com.lucky.shop.mobile.ucenter.domain.vo;

import com.lucky.shop.common.core.utils.StringUtil;
import lombok.Data;

import java.util.Date;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/24 13:06
 */
@Data
public class UserInfo {
    private String mobile;
    private String avatar;
    private String nickName;
    private Date lastLoginTime;
    private String gender;
    private String wechatNickName;
    private String wechatHeadImgUrl;

    public String getNickName() {
        return StringUtil.isEmpty(nickName) ? wechatNickName : nickName;
    }
}
