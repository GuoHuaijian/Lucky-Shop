package com.lucky.shop.admin.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.cms.domain.CmsChannel;

import java.util.List;

/**
 * 文章栏目
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:31
 */
public interface CmsChannelService extends IService<CmsChannel> {

    /**
     * 编辑栏目
     *
     * @param channel
     */
    void saveChannel(CmsChannel channel);

    /**
     * 删除栏目
     *
     * @param id
     */
    void remove(Long id);

    /**
     * 栏目列表
     *
     * @returnl
     */
    List<CmsChannel> channelList();

}
