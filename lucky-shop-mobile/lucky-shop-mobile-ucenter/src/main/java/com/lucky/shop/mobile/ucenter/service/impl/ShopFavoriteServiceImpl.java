package com.lucky.shop.mobile.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.mobile.ucenter.domain.ShopFavorite;
import com.lucky.shop.mobile.ucenter.domain.ShopUser;
import com.lucky.shop.mobile.ucenter.mapper.ShopFavoriteMapper;
import com.lucky.shop.mobile.ucenter.service.ShopFavoriteService;
import com.lucky.shop.mobile.ucenter.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 用户收藏
 *
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:42
 */
@Service
public class ShopFavoriteServiceImpl extends ServiceImpl<ShopFavoriteMapper, ShopFavorite> implements ShopFavoriteService {

    @Autowired
    private ShopUserService userService;

    /**
     * 添加收藏
     *
     * @param idGoods
     */
    @Override
    public void add(Long idGoods) {
        Long idUser = getCurrentUser().getId();
        QueryWrapper<ShopFavorite> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopFavorite.COL_ID_USER, idUser);
        wrapper.eq(ShopFavorite.COL_ID_GOODS, idGoods);
        ShopFavorite old = this.getOne(wrapper);
        if (old != null) {
            return;
        }
        ShopFavorite favorite = new ShopFavorite();
        favorite.setIdUser(idUser);
        favorite.setIdGoods(idGoods);
        this.save(favorite);
    }

    /**
     * 获取收藏
     *
     * @param idGoods
     * @return
     */
    @Override
    public ShopFavorite ifLike(Long idGoods) {
        Long idUser = getCurrentUser().getId();
        QueryWrapper<ShopFavorite> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopFavorite.COL_ID_USER, idUser);
        wrapper.eq(ShopFavorite.COL_ID_GOODS, idGoods);
        ShopFavorite favorite = this.getOne(wrapper);
        return favorite;
    }

    private ShopUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        QueryWrapper<ShopUser> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopUser.COL_MOBILE, account);
        ShopUser user = userService.getOne(wrapper);
        return user;
    }
}
