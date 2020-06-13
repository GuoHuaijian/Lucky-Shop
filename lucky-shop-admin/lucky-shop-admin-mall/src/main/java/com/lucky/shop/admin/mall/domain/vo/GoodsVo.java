package com.lucky.shop.admin.mall.domain.vo;

import com.lucky.shop.admin.mall.domain.ShopGoods;
import com.lucky.shop.admin.mall.domain.ShopGoodsSku;
import lombok.Data;

import java.util.List;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/13 23:14
 */
@Data
public class GoodsVo {

    private ShopGoods goods;

    private List<ShopGoodsSku> skuList;

}
