package com.lucky.shop.common.enums;

/**
 * 返回状态码和信息
 *
 * @author Guo Huaijian
 * @Date 2020/5/16 22:42
 */
public enum CodeStatus {

    /**
     * 成功
     */
    SUCCESS(20000, "操作成功"),

    /**
     * 失败
     */
    FAILURE(9999, "操作失败"),

    /**
     * 授权过期
     */
    TOKEN_EXPIRE(50014, "token 过期");

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    CodeStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
