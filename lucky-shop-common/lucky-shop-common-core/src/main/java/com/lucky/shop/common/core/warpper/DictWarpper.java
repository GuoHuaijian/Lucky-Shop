package com.lucky.shop.common.core.warpper;

import com.lucky.shop.common.core.utils.ToolUtil;

import java.util.Map;

/**
 * 字典列表的包装
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 18:56
 */
public class DictWarpper extends BaseControllerWarpper {

    public DictWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        StringBuffer detail = new StringBuffer();
        Long id = (Long) map.get("id");
//        List<SysDict> dicts = ConstantFactory.me().findInDict(id);
//        if(dicts != null){
//            for (Dict dict : dicts) {
//                detail.append(dict.getNum() + ":" +dict.getName() + ",");
//            }
        map.put("detail", ToolUtil.removeSuffix(detail.toString(), ","));
    }
}

