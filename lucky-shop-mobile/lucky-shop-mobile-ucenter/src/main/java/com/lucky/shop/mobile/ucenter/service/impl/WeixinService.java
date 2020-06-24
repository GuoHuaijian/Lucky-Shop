package com.lucky.shop.mobile.ucenter.service.impl;

import com.lucky.shop.admin.system.api.RemoteSysCfgService;
import com.lucky.shop.admin.system.api.domain.SysCfg;
import com.lucky.shop.common.core.constant.CfgKey;
import com.lucky.shop.common.core.utils.HttpUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.mobile.ucenter.domain.ShopUser;
import com.lucky.shop.mobile.ucenter.domain.WechatInfo;
import com.lucky.shop.mobile.ucenter.service.ShopUserService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.http.Http;
import org.nutz.json.Json;
import org.nutz.mapl.Mapl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/24 12:27
 */
@Service
@Slf4j
public class WeixinService {

    @Autowired
    private RemoteSysCfgService cfgService;

    @Autowired
    private ShopUserService userService;

    public void updateWeixinToken() {
        HttpUtil.trustEveryone();
        String accessToken = getAccessToken();
        String jsapiTicket = getJsapiTicket(accessToken);
        SysCfg tokenCfg = cfgService.getByCfgName(CfgKey.WX_ACCESS_TOKEN);
        if (tokenCfg == null) {
            tokenCfg = new SysCfg();
            tokenCfg.setCfgName(CfgKey.WX_ACCESS_TOKEN);
            tokenCfg.setCfgDesc("微信token，通过定时任务获取");
        }
        tokenCfg.setCfgValue(accessToken);
        cfgService.saveOrUpdate(tokenCfg);
        SysCfg tidCfg = cfgService.getByCfgName(CfgKey.WX_JS_API_TICKET);
        if (tidCfg == null) {
            tidCfg = new SysCfg();
            tidCfg.setCfgName(CfgKey.WX_JS_API_TICKET);
            tidCfg.setCfgDesc("微信ticket，通过定时任务获取");
        }
        tidCfg.setCfgValue(jsapiTicket);
        cfgService.saveOrUpdate(tidCfg);
    }

    public String getAccessToken() {
        String appId = cfgService.getCfgValue(CfgKey.WX_APP_ID);
        String appSecret = cfgService.getCfgValue(CfgKey.WX_APP_SECRET);
        String accessTokenUrl = cfgService.getCfgValue(CfgKey.WX_ACCESS_TOKEN_URL);
        String url = String.format(accessTokenUrl, appId, appSecret);
        String result = Http.get(url).getContent();
        log.info("获取微信token，\r\nurl : {},\r\n result : {}", url, result);
        Object object = Json.fromJson(StringUtil.sNull(result));
        String access_token = (String) Mapl.cell(object, "access_token");
        return access_token;
    }

    public String getJsapiTicket(String accessToken) {
        String jsapiTicketUrl = cfgService.getCfgValue(CfgKey.WX_JS_API_TICKET_URL);
        String url = String.format(jsapiTicketUrl, accessToken);
        String result = Http.get(url).getContent();
        Object object = Json.fromJson(StringUtil.sNull(result));
        String ticket = (String) Mapl.cell(object, "ticket");
        return ticket;
    }

    public Map<String, Object> getPrivateAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        url += "?appid=" + cfgService.getCfgValue(CfgKey.WX_APP_ID);
        url += "&secret=" + cfgService.getCfgValue(CfgKey.WX_APP_SECRET);
        url += "&code=" + code;
        url += "&grant_type=authorization_code";
        try {
            String result = Http.get(url).getContent();
            Object object = Json.fromJson(StringUtil.sNull(result));

            log.info("url:" + url + ";  response:" + Json.toJson(object));
            String access_token = (String) Mapl.cell(object, "access_token");
            if (StringUtil.isNotEmpty(access_token)) {
                return (Map) object;
            }
        } catch (Exception e) {
            log.error("获取token失败", e);
        }
        return null;
    }

    public boolean isAuth(ShopUser user, String code) {
        WechatInfo wechatInfo = null;
        if (StringUtil.isNotEmpty(code)) {
            // 第二步，通过code换取access_token和OpenId
            Map<String, Object> ret = getPrivateAccessToken(code);
            if (ret != null && ret.get("errcode") == null) {
                String openId = StringUtil.sNull(ret.get("openid"));
                log.info("用户:{}的openId:{}", user.getMobile(), openId);
                user.setWechatOpenId(openId);
                wechatInfo = getWechatInfo(openId);
                if (wechatInfo != null) {
                    user.setWechatNickName(StringUtil.getValidChar(wechatInfo.getNickName()));
                    user.setWechatHeadImgUrl(wechatInfo.getHeadUrl());
                    userService.save(user);
                    return true;
                }
            }
        }
        return false;
    }

    public WechatInfo getWechatInfo(String openId) {
        String accessToken = cfgService.getCfgValue(CfgKey.WX_ACCESS_TOKEN);
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId;
        String result = Http.get(url).getContent();
        Object object = Json.fromJson(StringUtil.sNull(result));
        log.info("getWecchatInfo====url:" + url + ";  response:" + Json.toJson(object));
        if (Mapl.cell(object, "errcode") != null) {
            log.info("获取微信用户基本信息失败", Mapl.cell(object, "errmsg"));
        } else {
            if (StringUtil.equals(StringUtil.sNull(Mapl.cell(object, "errcode")), "0")) {
                log.error("用户:{}没有关注该公众号", openId);
            } else {
                WechatInfo wechatInfo = new WechatInfo();
                wechatInfo.setOpenId(openId);
                wechatInfo.setNickName(StringUtil.sNull(Mapl.cell(object, "nickname")));
                wechatInfo.setHeadUrl(StringUtil.sNull(Mapl.cell(object, "headimgurl")));
                return wechatInfo;
            }
        }
        return null;
    }

    private String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    private String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public Map<String, String> getSign(String url) {
        Map<String, String> map = getSign(cfgService.getCfgValue(CfgKey.WX_JS_API_TICKET), url);
        map.put("appId", cfgService.getCfgValue(CfgKey.WX_APP_ID));
        return map;
    }

    public Map<String, String> getSign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = createNonceStr();
        String timestamp = createTimestamp();
        String string1;
        String signature = "";

        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
        log.info(string1);
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }
}
