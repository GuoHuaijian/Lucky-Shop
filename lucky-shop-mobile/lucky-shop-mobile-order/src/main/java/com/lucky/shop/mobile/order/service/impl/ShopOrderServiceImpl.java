package com.lucky.shop.mobile.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.common.core.enums.OrderEnum;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.tool.Maps;
import com.lucky.shop.common.core.utils.DateUtil;
import com.lucky.shop.common.core.utils.Lists;
import com.lucky.shop.common.core.utils.RandomUtil;
import com.lucky.shop.mobile.order.domain.ShopOrder;
import com.lucky.shop.mobile.order.domain.ShopOrderItem;
import com.lucky.shop.mobile.order.domain.ShopOrderLog;
import com.lucky.shop.mobile.order.mapper.ShopOrderMapper;
import com.lucky.shop.mobile.order.service.ShopOrderItemService;
import com.lucky.shop.mobile.order.service.ShopOrderLogService;
import com.lucky.shop.mobile.order.service.ShopOrderService;
import com.lucky.shop.mobile.ucenter.api.RemoteShopAddressService;
import com.lucky.shop.mobile.ucenter.api.RemoteShopCartService;
import com.lucky.shop.mobile.ucenter.api.RemoteShopUserService;
import com.lucky.shop.mobile.ucenter.api.domain.ShopAddress;
import com.lucky.shop.mobile.ucenter.api.domain.ShopCart;
import com.lucky.shop.mobile.ucenter.api.domain.ShopUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 22:28
 */
@Service
@Slf4j
public class ShopOrderServiceImpl extends ServiceImpl<ShopOrderMapper, ShopOrder> implements ShopOrderService {

    @Autowired
    private RemoteShopUserService shopUserService;

    @Autowired
    private RemoteShopCartService cartService;

    @Autowired
    private RemoteShopAddressService addressService;

    @Autowired
    private ShopOrderItemService orderItemService;

    @Autowired
    private ShopOrderLogService orderLogService;

    /**
     * 订单编号查询订单
     *
     * @param orderSn
     * @return
     */
    @Override
    public ShopOrder get(String orderSn) {
        QueryWrapper<ShopOrder> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopOrder.COL_ORDER_SN, orderSn);
        return this.getOne(wrapper);
    }

    /**
     * 根据状态查询订单
     *
     * @param status
     * @return
     */
    @Override
    public Page<ShopOrder> getOrders(Integer status) {
        Page<ShopOrder> page = new PageFactory<ShopOrder>().defaultPage();
        QueryWrapper<ShopOrder> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopOrder.COL_ID_USER, getUser().getId());
        wrapper.orderByDesc(ShopOrder.COL_ID);
        if (status != null && status != 0) {
            wrapper.eq(ShopOrder.COL_STATUS, status);
        }
        IPage<ShopOrder> orderPage = this.page(page, wrapper);
        return (Page<ShopOrder>) orderPage;
    }

    /**
     * 地址查询订单
     *
     * @param chosenAddressId
     * @return
     */
    @Override
    public Map prepareCheckout(Long chosenAddressId) {
        List<ShopCart> list = cartService.queryAll(getUser().getId());
        ShopAddress address = null;
        log.info("chosenAddressId：{}", chosenAddressId);
        if (chosenAddressId == null || chosenAddressId == 0) {
            address = addressService.getDefaultAddr(getUser().getId());
        } else {
            address = addressService.get(chosenAddressId);
        }
        return Maps.newHashMap("list", list, "addr", address);
    }

    /**
     * 保存订单
     *
     * @param idAddress
     * @param message
     * @return
     */
    @Override
    public ShopOrder save(Long idAddress, String message) {
        List<ShopCart> cartList = cartService.queryAll(getUser().getId());
        ShopOrder order = new ShopOrder();
        order.setIdUser(getUser().getId());
        order.setIdAddress(idAddress);
        BigDecimal totalPrice = new BigDecimal(0);
        List<ShopOrderItem> itemList = Lists.newArrayList();
        for (ShopCart cart : cartList) {
            ShopOrderItem orderItem = new ShopOrderItem();
            orderItem.setIdGoods(cart.getIdGoods());
            orderItem.setIdSku(cart.getIdSku());
            orderItem.setPrice(cart.getPrice());
            orderItem.setCount(cart.getCount());
            orderItem.setTotalPrice(orderItem.getPrice().multiply(orderItem.getCount()));
            totalPrice = totalPrice.add(orderItem.getTotalPrice());
            itemList.add(orderItem);
        }
        order.setMessage(message);
        order.setTotalPrice(totalPrice);
        order.setRealPrice(totalPrice);
        order.setStatus(OrderEnum.OrderStatusEnum.UN_PAY.getId());
        order.setPayStatus(OrderEnum.PayStatusEnum.UN_PAY.getId());
        this.insert(order, itemList);
        List<Long> cartIds = null;
        cartList.forEach(t -> {
            cartIds.add(t.getId());
        });
        cartService.deleteAll(cartIds);
        return order;
    }

    /**
     * 用户取消订单
     *
     * @param orderSn
     */
    @Override
    public void cancel(String orderSn) {
        ShopOrder order = getByOrderSn(orderSn);
        order.setStatus(OrderEnum.OrderStatusEnum.CANCEL.getId());
        String descript = "用户取消订单";
        saveOrderLog(order, descript);
        ;
        updateById(order);
    }

    /**
     * 确认收货
     *
     * @param orderSn
     * @return
     */
    @Override
    public ShopOrder confirm(String orderSn) {
        ShopOrder order = getByOrderSn(orderSn);
        order.setStatus(OrderEnum.OrderStatusEnum.FINISHED.getId());
        String descript = "客户确认收货";
        saveOrderLog(order, descript);
        updateById(order);
        return order;
    }

    /**
     * 发起支付
     *
     * @param order
     */
    @Override
    public void startPay(ShopOrder order) {
        order.setPayStatus(OrderEnum.PayStatusEnum.PAYING.getId());
        saveOrderLog(order, "客户发起支付");
        updateById(order);
    }

    /**
     * 支付成功，更新订单数据
     *
     * @param order
     * @param payType
     */
    @Override
    public void paySuccess(ShopOrder order, String payType) {
        order.setPayTime(new Date());
        order.setPayStatus(OrderEnum.PayStatusEnum.UN_SEND.getId());
        order.setStatus(OrderEnum.OrderStatusEnum.UN_SEND.getId());
        order.setRealPrice(order.getTotalPrice());
        order.setPayType(payType);
        updateById(order);
        String descript = "用户付款成功";
        saveOrderLog(order, descript);

    }

    /**
     * 获取用户
     *
     * @return
     */
    private ShopUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        ShopUser user = shopUserService.getUserByAccount(account);
        return user;
    }

    /**
     * 获取唯一订单号
     * 时间戳+随机数<br>
     * 建议生产环境使用redis获取唯一订单号
     *
     * @return
     */
    public String getOrderSn() {
        return DateUtil.getAllTime() + RandomUtil.getRandomNumber(6);
    }

    public void insert(ShopOrder order, List<ShopOrderItem> itemList) {
        order.setOrderSn(getOrderSn());
        this.save(order);
        for (ShopOrderItem item : itemList) {
            item.setIdOrder(order.getId());
        }
        orderItemService.saveBatch(itemList);
        ShopOrderLog orderLog = new ShopOrderLog();
        orderLog.setIdOrder(order.getId());
        orderLog.setDescript("生成订单");
        orderLogService.save(orderLog);
    }

    public ShopOrder getByOrderSn(String orderSn) {
        QueryWrapper<ShopOrder> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopOrder.COL_ORDER_SN, orderSn);
        return this.getOne(wrapper);
    }

    private void saveOrderLog(ShopOrder order, String descript) {
        ShopOrderLog orderLog = new ShopOrderLog();
        orderLog.setIdOrder(order.getId());
        orderLog.setDescript(descript);
        orderLogService.save(orderLog);
    }
}
