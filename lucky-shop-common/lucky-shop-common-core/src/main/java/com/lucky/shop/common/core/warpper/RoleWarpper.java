package com.lucky.shop.common.core.warpper;

import java.util.List;
import java.util.Map;

/**
 * 角色列表的包装类
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 23:23
 */
public class RoleWarpper extends BaseControllerWarpper {

    public RoleWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
//        map.put("pName", ConstantFactory.me().getSingleRoleName((Long) map.get("pid")));
//        map.put("deptName", ConstantFactory.me().getDeptName((Long) map.get("deptid")));
    }

}
