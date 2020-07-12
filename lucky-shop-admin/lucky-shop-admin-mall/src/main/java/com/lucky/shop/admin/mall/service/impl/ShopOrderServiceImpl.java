package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.*;
import com.lucky.shop.admin.mall.mapper.ShopOrderMapper;
import com.lucky.shop.admin.mall.service.*;
import com.lucky.shop.admin.mall.utils.SysUserUtils;
import com.lucky.shop.admin.system.api.RemoteSysExpressService;
import com.lucky.shop.admin.system.api.domain.SysExpress;
import com.lucky.shop.common.core.enums.OrderEnum;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.tool.Maps;
import com.lucky.shop.common.core.utils.DateUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/5 12:55
 */
@Service
public class ShopOrderServiceImpl extends ServiceImpl<ShopOrderMapper, ShopOrder> implements ShopOrderService {

    @Autowired
    private ShopOrderMapper orderMapper;

    @Autowired
    private ShopOrderLogService orderLogService;

    @Autowired
    private ShopUserService userService;

    @Autowired
    private ShopAddressService addressService;

    @Autowired
    private ShopOrderItemService orderItemService;

    @Resource
    private RemoteSysExpressService expressService;

    @Autowired
    private SysUserUtils sysUserUtils;

    /**
     * 获取订单统计信息
     *
     * @return
     */
    @Override
    public Map getOrderStatistic() {
        List<Map> list = orderMapper.getOrderStatistic();
        Map result = Maps.newHashMap();
        for (Map map : list) {
            String statusStr = OrderEnum.getStatusStr((Integer) map.get("status"));
            result.put(statusStr, map.get("count"));
        }
        return result;
    }

    /**
     * 保存订单日志
     *
     * @param order
     * @param descript
     */
    private void saveOrderLog(ShopOrder order, String descript) {
        ShopOrderLog orderLog = new ShopOrderLog();
        orderLog.setIdOrder(order.getId());
        orderLog.setDescript(descript);
        orderLogService.save(orderLog);
    }

    /**
     * 更新发货信息
     *
     * @param order
     */
    @Override
    public void send(ShopOrder order) {
        String descript = "管理员(" + sysUserUtils.getUsername() + ")已发货";
        updateById(order);
        saveOrderLog(order, descript);
    }

    /**
     * 管理员添加备注信息
     *
     * @param order
     * @param message
     */
    @Override
    public void addComment(ShopOrder order, String message) {
        order.setAdminMessage(message);
        updateById(order);
        ShopOrderLog orderLog = new ShopOrderLog();
        orderLog.setIdOrder(order.getId());
        orderLog.setDescript("管理员(" + sysUserUtils.getUsername() + ")添加备注：" + message);
        orderLogService.save(orderLog);
    }

    /**
     * 根据订单编号获取订单
     *
     * @param orderSn
     * @return
     */
    @Override
    public ShopOrder getByOrderSn(String orderSn) {
        QueryWrapper<ShopOrder> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopOrder.COL_ORDER_SN, orderSn);
        ShopOrder shopOrder = orderMapper.selectOne(wrapper);
        return shopOrder;
    }

    /**
     * 获取订单总金额
     *
     * @return
     */
    @Override
    public Map getRealPrice() {
        return orderMapper.getRealPrice();
    }

    /**
     * 获取订单列表
     *
     * @param mobile
     * @param orderSn
     * @param status
     * @param date
     * @param startDate
     * @param endDate
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<ShopOrder> orderList(String mobile, String orderSn, String status, String date, String startDate, String endDate) {
        Page<ShopOrder> page = new PageFactory<ShopOrder>().defaultPage();
        QueryWrapper<ShopOrder> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(orderSn)) {
            wrapper.eq(ShopOrder.COL_ORDER_SN, orderSn);
        }
        if (StringUtil.isNotEmpty(mobile)) {
            QueryWrapper<ShopUser> wrapper1 = new QueryWrapper<>();
            wrapper1.eq(ShopUser.COL_MOBILE, mobile);
            ShopUser shopUser = userService.getOne(wrapper1);
            wrapper.eq(ShopOrder.COL_ID_USER, shopUser.getId());
        }
        if (StringUtil.isNotEmpty(status)) {
            wrapper.eq(ShopOrder.COL_STATUS, OrderEnum.getStatusByStr(status));
        }
        if (StringUtil.isNotEmpty(date)) {
            Date[] rangeDate = DateUtil.getDateRange(date);
            wrapper.ge(ShopOrder.COL_CREATE_TIME, rangeDate[0]);
            wrapper.le(ShopOrder.COL_CREATE_TIME, rangeDate[1]);
        }
        if (StringUtil.isNotEmpty(startDate) && StringUtil.isNotEmpty(endDate)) {
            wrapper.ge(ShopOrder.COL_CREATE_TIME, DateUtil.parseDate(startDate));
            wrapper.le(ShopOrder.COL_CREATE_TIME, DateUtil.parseDate(endDate));
        }
        IPage<ShopOrder> result = this.page(page, wrapper);
        for (ShopOrder resultRecord : result.getRecords()) {
            QueryWrapper<ShopUser> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq(ShopUser.COL_ID,resultRecord.getIdUser());
            ShopUser user = userService.getOne(userQueryWrapper);
            resultRecord.setUser(user);
            QueryWrapper<ShopAddress> addressQueryWrapper = new QueryWrapper<>();
            addressQueryWrapper.eq(ShopAddress.COL_ID,resultRecord.getIdAddress());
            ShopAddress address = addressService.getOne(addressQueryWrapper);
            resultRecord.setAddress(address);
            QueryWrapper<ShopOrderItem> itemQueryWrapper = new QueryWrapper<>();
            itemQueryWrapper.eq(ShopOrderItem.COL_ID_ORDER,resultRecord.getId());
            List<ShopOrderItem> items = orderItemService.list(itemQueryWrapper);
            resultRecord.setItems(items);
            if (!StringUtil.isEmpty(resultRecord.getIdExpress())) {
                SysExpress express = expressService.getExpressById(resultRecord.getIdExpress());
                resultRecord.setExpress(express);
            }
        }
        result.setRecords(result.getRecords());
        return (Page<ShopOrder>) result;
    }

}
