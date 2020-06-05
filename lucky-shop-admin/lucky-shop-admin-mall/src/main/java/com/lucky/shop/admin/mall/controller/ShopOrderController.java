package com.lucky.shop.admin.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.mall.domain.ShopOrder;
import com.lucky.shop.admin.mall.domain.ShopUser;
import com.lucky.shop.admin.mall.service.ShopOrderService;
import com.lucky.shop.admin.mall.service.ShopUserService;
import com.lucky.shop.admin.system.api.FileServiceApi;
import com.lucky.shop.admin.system.api.SysCfgServiceApi;
import com.lucky.shop.admin.system.api.domain.SysFileInfo;
import com.lucky.shop.common.dto.ResponseResult;
import com.lucky.shop.common.enums.OrderEnum;
import com.lucky.shop.common.factory.PageFactory;
import com.lucky.shop.common.tool.Maps;
import com.lucky.shop.common.utils.DateUtil;
import com.lucky.shop.common.utils.Lists;
import com.lucky.shop.common.utils.StringUtil;
import com.sun.jndi.toolkit.dir.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 12:59
 */
@RestController
@RequestMapping("/shop/order")
public class ShopOrderController {

    @Autowired
    private ShopOrderService orderService;

    @Autowired
    private ShopUserService userService;

    @Autowired
    private FileServiceApi fileService;

//    @Autowired
//    private KdniaoService kdniaoService;

    @Autowired
    private SysCfgServiceApi cfgService;

    /**
     * 获取订单统计信息
     * todo 真实生产可以考虑将订单数量信息通过队列形式更新在redis等缓存中，然后从缓存获取，这里暂时从数据库直接统计
     *
     * @return
     */
    @GetMapping(value = "/getOrderStatistic")
    public ResponseResult getOrderStatistic() {
        Map result = orderService.getOrderStatistic();
        return ResponseResult.success(result);
    }

    /**
     * 获取订单列表
     * @param mobile
     * @param orderSn
     * @param status
     * @param date
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseResult list(@RequestParam(value = "mobile", required = false) String mobile,
                       @RequestParam(value = "orderSn", required = false) String orderSn,
                       @RequestParam(value = "status", required = false) String status,
                       @RequestParam(value = "date", required = false) String date,
                       @RequestParam(value = "startDate", required = false) String startDate,
                       @RequestParam(value = "endDate", required = false) String endDate) {

        Page<ShopOrder> page = new PageFactory<ShopOrder>().defaultPage();
        QueryWrapper<ShopOrder> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(orderSn)){
            wrapper.eq(ShopOrder.COL_ORDER_SN,orderSn);
        }
        if (StringUtil.isNotEmpty(mobile)){
            QueryWrapper<ShopUser> wrapper1 = new QueryWrapper<>();
            wrapper1.eq(ShopUser.COL_MOBILE,mobile);
            ShopUser shopUser = userService.getOne(wrapper1);
            wrapper.eq(ShopOrder.COL_ID_USER,shopUser.getId());
        }
        if(StringUtil.isNotEmpty(status)){
            wrapper.eq(ShopOrder.COL_STATUS,OrderEnum.getStatusByStr(status));
        }
        if(StringUtil.isNotEmpty(date)){
            Date[] rangeDate = DateUtil.getDateRange(date);
            wrapper.ge(ShopOrder.COL_CREATE_TIME,rangeDate[0]);
            wrapper.le(ShopOrder.COL_CREATE_TIME,rangeDate[1]);
        }
        if(StringUtil.isNotEmpty(startDate) && StringUtil.isNotEmpty(endDate)){
            wrapper.ge(ShopOrder.COL_CREATE_TIME,DateUtil.parseDate(startDate));
            wrapper.le(ShopOrder.COL_CREATE_TIME,DateUtil.parseDate(endDate));
        }
        IPage<ShopOrder> result = orderService.page(page, wrapper);
        return ResponseResult.success(result);
    }

    /**
     * 导出xlsx文件
     * @param mobile
     * @param orderSn
     * @return
     */
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public ResponseResult export(@RequestParam(value = "mobile", required = false) String mobile,
                         @RequestParam(value = "orderSn", required = false) String orderSn) {
        List<SearchFilter> filters = Lists.newArrayList();
        QueryWrapper<ShopOrder> wrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(mobile)){
            QueryWrapper<ShopUser> wrapper1 = new QueryWrapper<>();
            wrapper1.eq(ShopUser.COL_MOBILE,mobile);
            ShopUser shopUser = userService.getOne(wrapper1);
            wrapper.eq(ShopOrder.COL_ID_USER,shopUser.getId());
        }
        if(StringUtil.isNotEmpty(orderSn)){
           wrapper.eq(ShopOrder.COL_ORDER_SN,orderSn);
        }
        List<ShopOrder> orderList = orderService.list(wrapper);
        Map data =  Maps.newHashMap("list",orderList);
        String now = DateUtil.formatDate(new Date(),DateUtil.DATE_TIME_FMT);
        data.put("exportTime",now);
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        data.put("userName", account);
        SysFileInfo fileInfo = fileService.createExcel("templates/orderList.xlsx", "订单列表.xlsx", data);
        return ResponseResult.success(fileInfo);
    }

    /**
     * 更新订单
     * @param id
     * @param idExpress
     * @param shippingSn
     * @param shippingAmount
     * @return
     */
    @RequestMapping(value = "/sendOut/{id}", method = RequestMethod.POST)
    public ResponseResult sendOut(@PathVariable("id") Long id,
                          @RequestParam("idExpress") Long idExpress,
                          @RequestParam("shippingSn") String shippingSn,
                          @RequestParam(value = "shippingAmount",defaultValue = "0",required = false) String shippingAmount) {

        ShopOrder order = orderService.getById(id);
        order.setIdExpress(idExpress);
        order.setShippingSn(shippingSn);
        order.setShippingAmount(new BigDecimal(shippingAmount));
        order.setStatus(OrderEnum.OrderStatusEnum.SENDED.getId());
        orderService.send(order);
        return ResponseResult.success();
    }

//    @RequestMapping(value = "/comment/{id}", method = RequestMethod.POST)
//    public ResponseResult comment(@PathVariable("id") Long id,@RequestParam("message") String message) {
//        ShopOrder order = orderService.getById(id);
//        order.setAdminMessage(message);
//        orderService.addComment(order,message);
//        return ResponseResult.success();
//    }
//
//
//    @RequestMapping(value = "{orderSn}", method = RequestMethod.GET)
//    public Object get(@PathVariable("orderSn") String orderSn) {
//        if (orderSn == null) {
//            throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
//        }
//        Order order = orderService.getByOrderSn(orderSn);
//        return Rets.success(order);
//    }
//    @RequestMapping(value="/getShippingInfo/{shippingSn}/{shipperCode}")
//    public Object getShippingInfo(String shippingSn,String shipperCode){
//        String apiKdniaoUserid = cfgService.getCfgValue(CfgKey.API_KDNIAO_USERID);
//        if (StringUtil.isEmpty(apiKdniaoUserid)) {
//            return Rets.failure("你没有配置快递鸟服务参数");
//        }
//        KdniaoResponse response = kdniaoService.realTimeQuery(shippingSn,shipperCode);
//        return Rets.success(response);
//    }
}
