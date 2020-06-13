package com.lucky.shop.common.core.utils;

import com.alibaba.fastjson.JSON;

/**
 * Json工具
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 18:41
 */
public class JsonUtil {

    public static String toJsonString(Object obj) {
        return obj == null ? "" : JSON.toJSONString(obj);
    }

    public static Object fromJson(String jsonStr) {
        return StringUtil.isNotEmpty(jsonStr) ? JSON.parse(jsonStr) : null;
    }

    public static <T> T fromJson(Class<T> klass, String content) {
        return JSON.parseObject(content, klass);
    }
}
