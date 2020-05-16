package com.lucky.shop.common.dto;

import com.lucky.shop.common.enums.CodeStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 *统一返回结果集
 *
 * @Author Guo Huaijian
 * @Date 2020/5/16 22:33
 */
@Data
public class ResponseResult<T> extends HashMap<String, Object> implements Serializable{

        private static final long serialVersionUID = 3468352004150968551L;

        /**
         * 状态码
         */
        private Integer code;

        /**
         * 消息
         */
        private String message;

        /**
         * 返回对象
         */
        private T data;

        public ResponseResult() {
            super();
        }

        public ResponseResult(Integer code) {
            super();
            this.code = code;
        }

        public ResponseResult(Integer code, String message) {
            super();
            this.code = code;
            this.message = message;
        }

        public ResponseResult(Integer code, Throwable throwable) {
            super();
            this.code = code;
            this.message = throwable.getMessage();
        }

        public ResponseResult(Integer code, T data) {
            super();
            this.code = code;
            this.data = data;
        }

        public ResponseResult(Integer code, String message, T data) {
            super();
            this.code = code;
            this.message = message;
            this.data = data;
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
     *返回成功消息
     *
     * @param code 状态码
     * @param data 数据对象
     * @param msg 返回内容
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

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((data == null) ? 0 : data.hashCode());
            result = prime * result + ((message == null) ? 0 : message.hashCode());
            result = prime * result + ((code == null) ? 0 : code.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ResponseResult<?> other = (ResponseResult<?>) obj;
            if (data == null) {
                if (other.data != null) {
                    return false;
                }
            } else if (!data.equals(other.data)) {
                return false;
            }
            if (message == null) {
                if (other.message != null) {
                    return false;
                }
            } else if (!message.equals(other.message)) {
                return false;
            }
            if (code == null) {
                if (other.code != null) {
                    return false;
                }
            } else if (!code.equals(other.code)) {
                return false;
            }
            return true;
        }
}
