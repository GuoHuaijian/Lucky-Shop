package com.lucky.shop.common.dto;

import com.lucky.shop.common.enums.CodeStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 统一返回结果集
 *
 * @Author Guo Huaijian
 * @Date 2020/5/16 22:33
 */
@Data
public class ResponseResult<T> extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 ResponseResult 对象，使其表示一个空消息。
     */
    public ResponseResult() {
    }

    /**
     * 初始化一个新创建的 ResponseResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public ResponseResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 BaseResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ResponseResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResponseResult success() {
        return ResponseResult.success(CodeStatus.SUCCESS.getMsg());
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResponseResult success(Object data) {
        return ResponseResult.success(CodeStatus.SUCCESS.getMsg(), data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResponseResult success(String msg) {
        return ResponseResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseResult success(String msg, Object data) {
        return new ResponseResult(CodeStatus.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param code 状态码
     * @return 成功消息
     */
    public static ResponseResult success(String msg, int code) {
        return new ResponseResult(code, msg);
    }

    /**
     * 返回成功消息
     *
     * @param code 状态码
     * @param data 数据对象
     * @param msg  返回内容
     * @return
     */
    public static ResponseResult success(int code, Object data, String msg) {
        return new ResponseResult(code, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResponseResult error() {
        return ResponseResult.error(CodeStatus.FAILURE.getMsg());
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseResult error(String msg) {
        return ResponseResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseResult error(String msg, Object data) {
        return new ResponseResult(CodeStatus.FAILURE.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static ResponseResult error(int code, String msg) {
        return new ResponseResult(code, msg, null);
    }

    /**
     * token过期
     *
     * @return
     */
    public static ResponseResult tokenExpire() {
        return new ResponseResult(CodeStatus.TOKEN_EXPIRE.getCode(), CodeStatus.TOKEN_EXPIRE.getMsg(), null);
    }
}
