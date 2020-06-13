package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.ShopOrderLog;
import com.lucky.shop.admin.mall.mapper.ShopOrderLogMapper;
import com.lucky.shop.admin.mall.service.ShopOrderLogService;
import com.lucky.shop.common.core.factory.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单日志
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 17:59
 */
@Service
public class ShopOrderLogServiceImpl extends ServiceImpl<ShopOrderLogMapper, ShopOrderLog> implements ShopOrderLogService {

    @Autowired
    private ShopOrderLogMapper orderLogMapper;

    /**
     * 根据id获取订单日志
     *
     * @param idOrder
     * @return
     */
    @Override
    public List<ShopOrderLog> queryByIdOrder(Long idOrder) {
        QueryWrapper<ShopOrderLog> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopOrderLog.COL_ID_ORDER, idOrder);
        List<ShopOrderLog> logList = orderLogMapper.selectList(wrapper);
        return logList;
    }

    /**
     * 分页查询订单日志
     *
     * @return
     */
    @Override
    public Page<ShopOrderLog> ShopOrderLogList() {
        Page<ShopOrderLog> page = new PageFactory<ShopOrderLog>().defaultPage();
        IPage<ShopOrderLog> shopOrderLogIPage = this.page(page);
        return (Page<ShopOrderLog>) shopOrderLogIPage;
    }
}
