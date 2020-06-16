package com.lucky.shop.admin.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.cms.domain.CmsChannel;
import com.lucky.shop.admin.cms.mapper.CmsChannelMapper;
import com.lucky.shop.admin.cms.service.CmsChannelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章栏目
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 14:31
 */
@Service
public class CmsChannelServiceImpl extends ServiceImpl<CmsChannelMapper, CmsChannel> implements CmsChannelService{

    /**
     * 编辑栏目
     *
     * @param channel
     */
    @Override
    public void saveChannel(CmsChannel channel) {
        if(channel.getId()==null) {
            this.save(channel);
        }else{
            this.updateById(channel);
        }
    }

    /**
     * 删除栏目
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        this.removeById(id);
    }

    /**
     * 栏目列表
     *
     * @return
     */
    @Override
    public List<CmsChannel> channelList() {
        List<CmsChannel> list = this.list();
        return list;
    }
}
