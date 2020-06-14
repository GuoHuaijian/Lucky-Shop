package com.lucky.shop.common.core.enums;

/**
 * 是否是菜单的枚举
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 20:09
 */
public enum IsMenu {

    /**
     * 是
     */
    YES(1, "是"),

    /**
     * 不是
     */
    NO(0, "不是");

    int code;
    String message;

    IsMenu(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        } else {
            for (IsMenu s : IsMenu.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
