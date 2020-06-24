package com.lucky.shop.mobile.ucenter.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/24 12:50
 */
@Data
public class WechatInfo implements Serializable {
    private String nickName;
    private String headUrl;
    private String openId;
}
