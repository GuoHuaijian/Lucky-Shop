package com.lucky.shop.common.core.utils;

import java.util.Base64;

/**
 * 加密工具类
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 15:45
 */
public class CryptUtil {

    public static String encodeBASE64(byte[] bytes) {

        String encode = Base64.getEncoder().encodeToString(bytes);
        encode = encode.replaceAll("\n", "");
        return encode;
    }


}
