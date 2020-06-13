package com.lucky.shop.common.core.enums;

/**
 * 业务是否成功的日志记录
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 1:48
 */
public enum LogSucceed {

    /**
     * 成功
     */
    SUCCESS("成功"),

    /**
     * 失败
     */
    FAIL("失败");

    String message;

    LogSucceed(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
