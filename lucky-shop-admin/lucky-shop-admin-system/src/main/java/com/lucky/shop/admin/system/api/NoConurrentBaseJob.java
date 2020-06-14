package com.lucky.shop.admin.system.api;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.stereotype.Component;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/15 0:07
 */
@Component
@DisallowConcurrentExecution
public class NoConurrentBaseJob extends BaseJob {
}
