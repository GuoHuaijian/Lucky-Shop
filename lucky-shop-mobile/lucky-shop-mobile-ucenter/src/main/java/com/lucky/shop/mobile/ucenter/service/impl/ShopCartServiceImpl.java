package com.lucky.shop.mobile.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.mobile.product.api.RemoteShopGoodsService;
import com.lucky.shop.mobile.product.api.RemoteShopGoodsSkuService;
import com.lucky.shop.mobile.product.api.domain.ShopGoods;
import com.lucky.shop.mobile.product.api.domain.ShopGoodsSku;
import com.lucky.shop.mobile.ucenter.domain.ShopCart;
import com.lucky.shop.mobile.ucenter.domain.ShopUser;
import com.lucky.shop.mobile.ucenter.domain.vo.CartVo;
import com.lucky.shop.mobile.ucenter.mapper.ShopCartMapper;
import com.lucky.shop.mobile.ucenter.service.ShopCartService;
import com.lucky.shop.mobile.ucenter.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:03
 */
@Service
public class ShopCartServiceImpl extends ServiceImpl<ShopCartMapper, ShopCart> implements ShopCartService {

    @Autowired
    private ShopUserService userService;

    @Autowired
    private RemoteShopGoodsService goodsService;

    @Autowired
    private RemoteShopGoodsSkuService goodsSkuService;

    /**
     * 获取用户购物车
     *
     * @return
     */
    @Override
    public List<ShopCart> getByUser() {
        Long idUser = getCurrentUser().getId();
        QueryWrapper<ShopCart> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopCart.COL_ID_USER, idUser);
        List<ShopCart> list = this.list(wrapper);
        return list;
    }

    /**
     * 添加购物车
     *
     * @param cartVo
     * @return
     */
    @Override
    public Integer add(CartVo cartVo) {
        Long idUser = getCurrentUser().getId();
        cartVo.setIdUser(idUser);
        Integer result = this.addCart(cartVo);
        return result;
    }

    /**
     * 购物车数量
     *
     * @return
     */
    @Override
    public int cartCount() {
        Long idUser = getCurrentUser().getId();
        QueryWrapper<ShopCart> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopCart.COL_ID_USER, idUser);
        List<ShopCart> list = this.list(wrapper);
        return list.size();
    }

    /**
     * 修改购物车
     *
     * @param id
     * @param count
     */
    @Override
    public void update(Long id, String count) {
        ShopCart cart = this.getById(id);
        cart.setCount(new BigDecimal(count));
        CartVo cartVo = new CartVo();
        cartVo.setIdUser(cart.getIdUser());
        // 修改订单中的数量的时候统一走cartService.add方法以便进行库存数量判断，避免超卖，
        cartVo.setCount(Integer.valueOf(count) - cart.getCount().intValue());
        cartVo.setIdGoods(cart.getIdGoods());
        cartVo.setIdSku(cart.getIdSku());
        this.addCart(cartVo);
    }

    /**
     * 删除购物车
     *
     * @param id
     * @return
     */
    @Override
    public void remove(Long id) {
        Long idUser = getCurrentUser().getId();
        ShopCart cart = this.getById(id);
        if (cart.getIdUser().intValue() == idUser.intValue()) {
            this.removeById(cart);
        }
    }

    /**
     * 查询用户购物车
     *
     * @param idUser
     * @return
     */
    @Override
    public List<ShopCart> queryAll(String idUser) {
        QueryWrapper<ShopCart> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopCart.COL_ID_USER, idUser);
        return this.list(wrapper);
    }

    /**
     * 删除全部购物车
     *
     * @param id
     */
    @Override
    public void deleteAll(List<Long> id) {
        this.removeByIds(id);
    }

    private ShopUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        QueryWrapper<ShopUser> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopUser.COL_MOBILE, account);
        ShopUser user = userService.getOne(wrapper);
        return user;
    }

    /**
     * 添加到购物车<br>
     * 1,这里需要考虑库存管控的问题
     * 2，实际项目中如果需要部署服务集群，那么下面synchronized关键字一点用是没有的，可以考虑使用分布式锁（zookeeper，redis等）来处理
     *
     * @param cartVo
     * @return，添加新商品，返回1，添加购物车已经存在的商品，返回0
     */
    @Transactional
    public synchronized Integer addCart(CartVo cartVo) {
        Integer count = cartVo.getCount();
        Long idSku = cartVo.getIdSku();
        QueryWrapper<ShopCart> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopCart.COL_ID_USER, cartVo.getIdUser());
        wrapper.eq(ShopCart.COL_ID_GOODS, cartVo.getIdGoods());
        if (idSku != null) {
            wrapper.eq(ShopCart.COL_ID_SKU, idSku);
        }
        ShopCart old = getOne(wrapper);
        Integer result = 0;
        if (old != null) {
            // 判断之前是否添加到购物车，如果已添加，则在原有基础上增加购买数量即可
            old.setCount(old.getCount().add(new BigDecimal(count)));
            updateById(old);
        } else {
            // 购物车新增商品
            ShopCart cart = new ShopCart();
            cart.setIdGoods(cartVo.getIdGoods());
            cart.setCount(new BigDecimal(count));
            cart.setIdUser(cartVo.getIdUser());
            cart.setIdSku(idSku);
            save(cart);
            result = 1;
        }
        // 减库存
        if (idSku != null) {
            ShopGoodsSku goodsSku = goodsSkuService.getOne(idSku);
            if (goodsSku.getStock() < count) {
                throw new RuntimeException("库存不足");
            }
            goodsSku.setStock(goodsSku.getStock() - count);
        }
        ShopGoods goods = goodsService.getOne(cartVo.getIdGoods());
        if (goods.getStock() < count) {
            throw new RuntimeException("库存不足");
        }
        goods.setStock(goods.getStock() - count);
        goodsService.save(goods);
        return result;
    }
}
