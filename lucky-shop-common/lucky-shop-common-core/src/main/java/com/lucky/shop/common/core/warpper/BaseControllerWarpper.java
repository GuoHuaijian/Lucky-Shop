package com.lucky.shop.common.core.warpper;

import java.util.List;
import java.util.Map;

/**
 * 控制器查询结果的包装类基类
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 13:29
 */
public abstract class BaseControllerWarpper {

    public Object obj = null;

    public BaseControllerWarpper(Object obj) {
        this.obj = obj;
    }

    @SuppressWarnings("unchecked")
    public Object warp() {

        if (this.obj instanceof List) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) this.obj;
            for (Map<String, Object> map : list) {
                warpTheMap(map);
            }
            return list;
        } else if (this.obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) this.obj;
            warpTheMap(map);
            return map;
        } else {
            return this.obj;
        }
    }

    /**
     * 数据转换
     *
     * @param map
     */
    protected abstract void warpTheMap(Map<String, Object> map);
}
