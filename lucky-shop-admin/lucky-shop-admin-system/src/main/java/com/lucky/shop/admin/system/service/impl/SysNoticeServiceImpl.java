package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.lucky.shop.admin.system.domain.SysNotice;
import com.lucky.shop.admin.system.mapper.SysNoticeMapper;
import com.lucky.shop.admin.system.service.SysNoticeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通知
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 21:58
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {

    /**
     * 通知列表
     *
     * @param condition
     * @return
     */
    @Override
    public List<SysNotice> list(String condition) {
        List<SysNotice> list;
        if (Strings.isNullOrEmpty(condition)) {
            list = this.list();
        } else {
            QueryWrapper<SysNotice> wrapper = new QueryWrapper<>();
            wrapper.like(SysNotice.COL_TITLE, condition);
            list = this.list(wrapper);
        }
        return list;
    }
}
