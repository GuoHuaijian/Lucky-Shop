package com.lucky.shop.mobile.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.common.core.factory.PageFactory;
import com.lucky.shop.common.core.tool.Maps;
import com.lucky.shop.common.core.utils.Lists;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.mobile.product.domain.ShopAttrKey;
import com.lucky.shop.mobile.product.domain.ShopAttrVal;
import com.lucky.shop.mobile.product.domain.ShopGoods;
import com.lucky.shop.mobile.product.domain.ShopGoodsSku;
import com.lucky.shop.mobile.product.mapper.ShopGoodsMapper;
import com.lucky.shop.mobile.product.service.ShopAttrKeyService;
import com.lucky.shop.mobile.product.service.ShopGoodsService;
import com.lucky.shop.mobile.product.service.ShopGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 1:46
 */
@Service
public class ShopGoodsServiceImpl extends ServiceImpl<ShopGoodsMapper, ShopGoods> implements ShopGoodsService {

    @Autowired
    private ShopGoodsSkuService goodsSkuService;

    @Autowired
    private ShopAttrKeyService attrKeyService;

    /**
     * 获取指定类别下的商品列表
     *
     * @param idCategory
     * @return
     */
    @Override
    public Page<ShopGoods> queryGoods(Long idCategory) {
        Page<ShopGoods> page = new PageFactory<ShopGoods>().defaultPage();
        QueryWrapper<ShopGoods> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopGoods.COL_ID_CATEGORY, idCategory);
        wrapper.eq(ShopGoods.COL_IS_ON_SALE, true);
        IPage<ShopGoods> goodsIPage = this.page(page, wrapper);
        return (Page<ShopGoods>) goodsIPage;
    }

    /**
     * 根据关键字搜索商品
     *
     * @param key
     * @return
     */
    @Override
    public Page<ShopGoods> search(String key) {
        Page<ShopGoods> page = new PageFactory<ShopGoods>().defaultPage();
        QueryWrapper<ShopGoods> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(key)) {
            wrapper.like(ShopGoods.COL_NAME, key);
        }
        wrapper.eq(ShopGoods.COL_IS_ON_SALE, true);
        IPage<ShopGoods> goodsIPage = this.page(page, wrapper);
        return (Page<ShopGoods>) goodsIPage;
    }

    /**
     * 查询新上架
     *
     * @return
     */
    @Override
    public List<ShopGoods> searchNew() {
        QueryWrapper<ShopGoods> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopGoods.COL_IS_NEW, true);
        List<ShopGoods> list = this.list(wrapper);
        return list;
    }

    /**
     * 查询热门商品
     *
     * @return
     */
    @Override
    public List<ShopGoods> searchHot() {
        QueryWrapper<ShopGoods> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopGoods.COL_IS_HOT, true);
        List<ShopGoods> list = this.list(wrapper);
        return list;
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @Override
    public Map get(Long id) {
        ShopGoods goods = this.getById(id);
        List<ShopGoodsSku> skuList = goodsSkuService.queryAll(id);
        Map skuMap = Maps.newHashMap();
        List<Map> tree = Lists.newArrayList();
        if (!skuList.isEmpty()) {
            List<ShopAttrVal> attrValList = Lists.newArrayList();
            List<ShopAttrKey> attrKeyList = attrKeyService.queryBy(goods.getIdCategory());
            for (ShopAttrKey attrKey : attrKeyList) {
                Map treeNode = Maps.newHashMap();
                treeNode.put("k", attrKey.getAttrName());
                List<Map> v = Lists.newArrayList();
                List<ShopAttrVal> attrValListChildren = attrKey.getAttrVals();
                attrValList.addAll(attrValListChildren);
                for (ShopAttrVal attrVal : attrValListChildren) {
                    v.add(Maps.newHashMap(
                            "id", attrVal.getId(),
                            "name", attrVal.getAttrVal(),
                            "plain", true
                    ));
                }
                treeNode.put("v", v);
                treeNode.put("k_s", "s" + attrKey.getId());
                tree.add(treeNode);
            }
            Map<Long, ShopAttrVal> attrValMap = Lists.toMap(attrValList, "id");
            List<Map> skuList2 = Lists.newArrayList();

            for (ShopGoodsSku sku : skuList) {
                Map oneSkuMap = Maps.newHashMap();
                oneSkuMap.put("id", sku.getId());
                oneSkuMap.put("price", sku.getPrice());
                String[] attrValIdArr = sku.getCode().split(",");
                for (String attrValId : attrValIdArr) {
                    ShopAttrVal attrVal = attrValMap.get(Long.valueOf(attrValId));
                    oneSkuMap.put("s" + attrVal.getIdAttrKey(), attrVal.getId());
                }
                oneSkuMap.put("stock_num", sku.getStock());
                oneSkuMap.put("code", sku.getCode());
                skuList2.add(oneSkuMap);
            }
            skuMap.put("list", skuList2);
            skuMap.put("price", skuList.get(0).getPrice());
            skuMap.put("collection_id", skuList.get(0).getId());
            skuMap.put("none_sku", false);
        } else {
            skuMap.put("price", goods.getPrice());
            skuMap.put("collection_id", goods.getId());
            skuMap.put("none_sku", true);
        }
        skuMap.put("tree", tree);
        skuMap.put("stock_num", goods.getStock());
        skuMap.put("hide_stock", false);
        return Maps.newHashMap("goods", goods, "sku", skuMap);
    }

}
