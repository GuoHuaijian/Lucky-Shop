package com.lucky.shop.common.enums;

/**
 * 返回状态码和信息
 * @author Guo Huaijian
 * @Date 2020/5/16 22:42
 */
public enum CodeStatus {

    SUCCESS(20000,"操作成功"),FAILURE(9999,"操作失败"),TOKEN_EXPIRE(50014,"token 过期");
    private Integer code;
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
    }
}
