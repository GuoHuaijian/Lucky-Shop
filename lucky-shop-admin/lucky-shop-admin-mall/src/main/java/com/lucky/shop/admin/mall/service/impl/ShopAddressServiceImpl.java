package com.lucky.shop.admin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.mall.domain.ShopAddress;
import com.lucky.shop.admin.mall.mapper.ShopAddressMapper;
import com.lucky.shop.admin.mall.service.ShopAddressService;
import com.lucky.shop.common.core.factory.PageFactory;
import org.springframework.stereotype.Service;

/**
 * 收获地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/13 19:36
 */
@Service
public class ShopAddressServiceImpl extends ServiceImpl<ShopAddressMapper, ShopAddress> implements ShopAddressService {

    /**
     * 根据用户id分页查询地址
     *
     * @param idUser
     * @return
     */
    @Override
    public Page<ShopAddress> addressList(Long idUser) {
        Page<ShopAddress> page = new PageFactory<ShopAddress>().defaultPage();
        QueryWrapper<ShopAddress> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAddress.COL_ID_USER, idUser);
        IPage<ShopAddress> addressIPage = this.page(page, wrapper);
        return (Page<ShopAddress>) addressIPage;
    }
}
