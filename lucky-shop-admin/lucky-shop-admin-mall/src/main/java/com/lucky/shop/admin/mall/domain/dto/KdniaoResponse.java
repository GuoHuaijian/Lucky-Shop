package com.lucky.shop.admin.mall.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 快递鸟查询返回数据封装
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 18:38
 */
@Data
public class KdniaoResponse {
    private Boolean Success;
    private String State;
    private String StateEx;
    private String Location;
    private List<Trace> Traces;

    @Data
    public
    class Trace {
        private String AcceptTime;
        private String AcceptStation;

    }
}
