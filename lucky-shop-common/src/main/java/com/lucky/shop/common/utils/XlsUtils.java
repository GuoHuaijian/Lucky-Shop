package com.lucky.shop.common.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/5 17:35
 */
public class XlsUtils {

    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        return DateUtil.formatDate(date, fmt);
    }

    /**
     * 将以分为单位的金额转换为人民币形式，比如74900转为为：￥749
     *
     * @param amount
     * @return
     */
    public String toRMB(BigDecimal amount) {
        if (amount == null) {
            return "";
        }
        return "￥" + amount.divide(new BigDecimal(100)).toPlainString();
    }
}
