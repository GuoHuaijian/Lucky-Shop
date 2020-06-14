package com.lucky.shop.admin.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/14 23:49
 */
@Getter
@Setter
public class QuartzJob implements Serializable {
    private String jobId;
    private String jobName;
    private String jobGroup;
    private String jobClass;
    private String description;
    private String cronExpression;
    private boolean concurrent;
    private String jobStatus;
    private Date nextTime;
    private Date previousTime;
    private boolean disabled;
    private Map<String, Object> dataMap;
}
