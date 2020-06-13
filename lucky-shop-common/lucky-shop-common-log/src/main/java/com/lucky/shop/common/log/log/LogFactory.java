package com.lucky.shop.common.log.log;

import com.lucky.shop.admin.ops.api.domain.LoginLog;
import com.lucky.shop.admin.ops.api.domain.OperationLog;
import com.lucky.shop.common.core.enums.LogSucceed;
import com.lucky.shop.common.core.enums.LogType;

import java.util.Date;

/**
 * 日志对象创建工厂
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 1:40
 */
public class LogFactory {

    /**
     * 创建操作日志
     *
     * @author fengshuonan
     * @Date 2017/3/30 18:45
     */
    public static OperationLog createOperationLog(LogType logType, Long userId, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogtype(logType.getMessage());
        operationLog.setLogname(bussinessName);
        operationLog.setUserid(userId.intValue());
        operationLog.setClassname(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreateTime(new Date());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建登录日志
     *
     * @author fengshuonan
     * @Date 2017/3/30 18:46
     */
    public static LoginLog createLoginLog(LogType logType, Long userId, String msg, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLogname(logType.getMessage());
        loginLog.setUserid(userId.intValue());
        loginLog.setCreateTime(new Date());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }

}
