package com.lucky.shop.mobile.ucenter.controller;

import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.mobile.ucenter.domain.ShopAddress;
import com.lucky.shop.mobile.ucenter.service.ShopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 收货地址
 *
 * @Author Guo Huaijian
 * @Date 2020/6/23 23:40
 */
@RestController
@RequestMapping("mobile/ucenter/user/address")
public class ShopAddressController {

    @Autowired
    private ShopAddressService addressService;

    /**
     * 获取地址
     *
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public ResponseResult getById(@PathVariable("id") Long id) {
        ShopAddress address = addressService.getById(id);
        return ResponseResult.success(address);

    }

    /**
     * 删除地址
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "{id}")
    public ResponseResult remove(@PathVariable("id") Long id) {
        addressService.remove(id);
        return ResponseResult.success();
    }

    /**
     * 修改默认地址
     *
     * @param id
     * @param isDefault
     * @return
     */
    @PostMapping(value = "{id}/{isDefault}")
    public ResponseResult changeDefault(@PathVariable("id") Long id, @PathVariable("isDefault") Boolean isDefault) {
        ShopAddress address = addressService.changeDefault(id, isDefault);
        return ResponseResult.success(address);

    }

    /**
     * 根据用户获取地址
     *
     * @return
     */
    @GetMapping(value = "/queryByUser")
    public ResponseResult getByUser() {
        List<ShopAddress> list = addressService.getByUser();
        return ResponseResult.success(list);
    }

    /**
     * 编辑地址
     *
     * @param addressInfo
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseResult save(@RequestBody @Valid ShopAddress addressInfo) {
        addressService.saveAddress(addressInfo);
        return ResponseResult.success();
    }


    /**
     * 获取用户默认收货地址
     *
     * @param idUser
     * @return
     */
    @GetMapping("/{idUser}")
    public ShopAddress getDefaultAddr(@PathVariable Long idUser) {
        return addressService.getDefaultAddr(idUser);
    }

    /**
     * 根据主键获取地址
     *
     * @param chosenAddressId
     * @return
     */
    @GetMapping("/{chosenAddressId}")
    public ShopAddress get(@PathVariable Long chosenAddressId) {
        return addressService.get(chosenAddressId);
    }
}
