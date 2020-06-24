package com.lucky.shop.mobile.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.mobile.ucenter.domain.ShopAddress;
import com.lucky.shop.mobile.ucenter.domain.ShopUser;
import com.lucky.shop.mobile.ucenter.mapper.ShopAddressMapper;
import com.lucky.shop.mobile.ucenter.service.ShopAddressService;
import com.lucky.shop.mobile.ucenter.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:40
 */
@Service
public class ShopAddressServiceImpl extends ServiceImpl<ShopAddressMapper, ShopAddress> implements ShopAddressService {

    @Autowired
    private ShopUserService userService;

    /**
     * 获取地址
     *
     * @param id
     * @return
     */
    @Override
    public ShopAddress getById(Long id) {
        Long idUser = getCurrentUser().getId();
        QueryWrapper<ShopAddress> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAddress.COL_ID_USER, idUser);
        wrapper.eq(ShopAddress.COL_ID, id);
        ShopAddress address = this.getOne(wrapper);
        return address;
    }

    /**
     * 删除地址
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        Long idUser = getCurrentUser().getId();
        QueryWrapper<ShopAddress> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAddress.COL_ID_USER, idUser);
        wrapper.eq(ShopAddress.COL_ID, id);
        ShopAddress address = this.getOne(wrapper);
        address.setIsDelete(true);
        updateById(address);
    }

    /**
     * 修改默认地址
     *
     * @param id
     * @param isDefault
     * @return
     */
    @Override
    public ShopAddress changeDefault(Long id, Boolean isDefault) {
        Long idUser = getCurrentUser().getId();
        ShopAddress defaultAddr = this.getDefaultAddr(idUser);
        if (defaultAddr != null) {
            if (defaultAddr.getId().intValue() == id.intValue()) {
                defaultAddr.setIsDefault(isDefault);
                this.updateById(defaultAddr);
                return null;
            } else {
                if (isDefault) {
                    defaultAddr.setIsDefault(false);
                    this.updateById(defaultAddr);
                }
            }
        }
        QueryWrapper<ShopAddress> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAddress.COL_ID_USER, idUser);
        wrapper.eq(ShopAddress.COL_ID, id);
        ShopAddress address = this.getOne(wrapper);
        address.setIsDefault(isDefault);
        this.updateById(address);
        return address;
    }

    /**
     * 根据用户获取地址
     *
     * @return
     */
    @Override
    public List<ShopAddress> getByUser() {
        Long idUser = getCurrentUser().getId();
        QueryWrapper<ShopAddress> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAddress.COL_ID_USER, idUser);
        wrapper.eq(ShopAddress.COL_IS_DELETE, false);
        List<ShopAddress> list = this.list(wrapper);
        return list;
    }

    /**
     * 编辑地址
     *
     * @param addressInfo
     */
    @Override
    public void saveAddress(ShopAddress addressInfo) {
        Long idUser = getCurrentUser().getId();
        addressInfo.setIdUser(idUser);
        if (addressInfo.getId() != null) {
            QueryWrapper<ShopAddress> wrapper = new QueryWrapper<>();
            wrapper.eq(ShopAddress.COL_ID_USER, idUser);
            wrapper.eq(ShopAddress.COL_ID, addressInfo.getId());
            ShopAddress old = this.getOne(wrapper);
            addressInfo.setCreateTime(old.getCreateTime());
            this.updateById(addressInfo);
        } else {
            this.save(addressInfo);
        }
    }

    /**
     * 获取用户默认收货地址
     *
     * @param idUser
     * @return
     */
    @Override
    public ShopAddress getDefaultAddr(Long idUser) {
        QueryWrapper<ShopAddress> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopAddress.COL_ID_USER, idUser);
        wrapper.eq(ShopAddress.COL_IS_DEFAULT, true);
        return this.getOne(wrapper);
    }

    /**
     * 根据主键获取地址
     *
     * @param chosenAddressId
     * @return
     */
    @Override
    public ShopAddress get(Long chosenAddressId) {
        return this.getById(chosenAddressId);
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
