package com.lucky.shop.mobile.order.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.shop.common.core.enums.OrderEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 22:28
 */
@Data
@TableName(value = "t_shop_order")
public class ShopOrder implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间/注册时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @TableField(value = "modify_time")
    private Date modifyTime;

    /**
     * 管理员备注
     */
    @TableField(value = "admin_message")
    private String adminMessage;

    /**
     * 确认收货时间
     */
    @TableField(value = "confirm_receiving_time")
    private Date confirmReceivingTime;

    /**
     * 优惠券抵扣金额
     */
    @TableField(value = "coupon_price")
    private String couponPrice;

    /**
     * 收货信息
     */
    @TableField(value = "id_address")
    private Long idAddress;

    /**
     * 快递公司
     */
    @TableField(value = "id_express")
    private Long idExpress;

    /**
     * 用户id
     */
    @TableField(value = "id_user")
    private Long idUser;

    /**
     * 订单备注
     */
    @TableField(value = "message")
    private String message;

    /**
     * 订单号
     */
    @TableField(value = "order_sn")
    private String orderSn;

    /**
     * 支付流水号
     */
    @TableField(value = "pay_id")
    private String payId;

    /**
     * 支付状态1:未付款;2:已付款,3:支付中
     */
    @TableField(value = "pay_status")
    private Integer payStatus;

    /**
     * 支付成功时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 实付类型:alipay,wechat
     */
    @TableField(value = "pay_type")
    private String payType;

    /**
     * 实付金额
     */
    @TableField(value = "real_price")
    private BigDecimal realPrice;

    /**
     * 配送费用
     */
    @TableField(value = "shipping_amount")
    private String shippingAmount;

    /**
     * 快递单号
     */
    @TableField(value = "shipping_sn")
    private String shippingSn;

    /**
     * 出库时间
     */
    @TableField(value = "shipping_time")
    private Date shippingTime;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 总金额
     */
    @TableField(value = "total_price")
    private BigDecimal totalPrice;

    /**
     * 交易金额
     */
    @TableField(value = "trade_amount")
    private String tradeAmount;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_TIME = "modify_time";

    public static final String COL_ADMIN_MESSAGE = "admin_message";

    public static final String COL_CONFIRM_RECEIVING_TIME = "confirm_receiving_time";

    public static final String COL_COUPON_PRICE = "coupon_price";

    public static final String COL_ID_ADDRESS = "id_address";

    public static final String COL_ID_EXPRESS = "id_express";

    public static final String COL_ID_USER = "id_user";

    public static final String COL_MESSAGE = "message";

    public static final String COL_ORDER_SN = "order_sn";

    public static final String COL_PAY_ID = "pay_id";

    public static final String COL_PAY_STATUS = "pay_status";

    public static final String COL_PAY_TIME = "pay_time";

    public static final String COL_PAY_TYPE = "pay_type";

    public static final String COL_REAL_PRICE = "real_price";

    public static final String COL_SHIPPING_AMOUNT = "shipping_amount";

    public static final String COL_SHIPPING_SN = "shipping_sn";

    public static final String COL_SHIPPING_TIME = "shipping_time";

    public static final String COL_STATUS = "status";

    public static final String COL_TOTAL_PRICE = "total_price";

    public static final String COL_TRADE_AMOUNT = "trade_amount";

    public Boolean hasPayed() {
        return OrderEnum.PayStatusEnum.UN_SEND.getId().equals(payStatus);
    }
}
