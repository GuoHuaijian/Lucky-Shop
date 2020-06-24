package com.lucky.shop.mobile.ucenter.domain.vo;

import lombok.Data;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/24 14:09
 */
@Data
public class CartVo {
    private Long idGoods;
    private Integer count;
    private Long idSku;
    private Long idUser;

}
