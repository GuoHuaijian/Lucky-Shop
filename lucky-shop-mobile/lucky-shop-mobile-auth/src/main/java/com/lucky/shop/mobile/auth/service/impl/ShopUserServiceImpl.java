package com.lucky.shop.mobile.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.message.api.RemoteMessageService;
import com.lucky.shop.common.core.enums.MessageTemplateEnum;
import com.lucky.shop.common.core.utils.MD5;
import com.lucky.shop.common.core.utils.RandomUtil;
import com.lucky.shop.common.core.utils.StringUtil;
import com.lucky.shop.common.redis.service.RedisService;
import com.lucky.shop.mobile.auth.domain.ShopUser;
import com.lucky.shop.mobile.auth.mapper.ShopUserMapper;
import com.lucky.shop.mobile.auth.service.ShopUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户
 *
 * @Author Guo Huaijian
 * @Date 2020/6/22 16:36
 */
@Service
@Slf4j
public class ShopUserServiceImpl extends ServiceImpl<ShopUserMapper, ShopUser> implements ShopUserService{

    @Resource
    private RedisService redisService;

    @Autowired
    private RemoteMessageService messageService;

    /**
     * 模拟发送短信
     *
     * @param mobile
     * @return
     */
    @Override
    public boolean  sendSmsCode(String mobile) {
        String smsCode = RandomUtil.getRandomNumber(4);
        String key =  mobile+"_smsCode";
        String timesKey = key+"_times";
        String oldSmsCode = redisService.getCacheObject(key);
        if(StringUtil.isNotEmpty(oldSmsCode)){
            log.info("{}:一分钟内已经发送过短信验证码，不再重复发送",oldSmsCode);
            throw  new RuntimeException();
        }
        Integer sendTimes =  redisService.getCacheObject(timesKey);
        sendTimes = sendTimes==null?0:sendTimes;
        if(sendTimes!=null&&sendTimes>3){
            log.info("{}:当天天发送短信验证码次数超限",mobile);
            throw  new RuntimeException();
        }
        redisService.setCacheObject(key,smsCode);
        redisService.setCacheObject(timesKey,sendTimes++);

        messageService.sendSms(MessageTemplateEnum.REGISTER_CODE.getCode(),mobile,smsCode);
        return true;
    }

    /**
     * 验证
     *
     * @param mobile
     * @param smsCode
     * @return
     */
    @Override
    public Boolean validateSmsCode(String mobile, String smsCode) {
        //todo 测试验证逻辑，暂不实现
        String key = mobile+"_smsCode";
        String smsCode2 = redisService.getCacheObject(key);
        return StringUtil.equals(smsCode,smsCode2);
    }

    /**
     * 通过手机号查询用户
     *
     * @param mobile
     * @return
     */
    @Override
    public ShopUser findByMobile(String mobile) {
        QueryWrapper<ShopUser> wrapper = new QueryWrapper<>();
        wrapper.eq(ShopUser.COL_MOBILE,mobile);
        return this.getOne(wrapper);
    }

    /**
     * 注册
     *
     * @param mobile
     * @param initPwd
     * @return
     */
    @Override
    public ShopUser register(String mobile, String initPwd) {
        ShopUser user = new ShopUser();
        user.setMobile(mobile);
        user.setNickName(mobile);
        user.setCreateTime(new Date());
        user.setSalt(RandomUtil.getRandomString(5));
        user.setPassword(MD5.md5(initPwd, user.getSalt()));
        this.save(user);
        return user;
    }
}
