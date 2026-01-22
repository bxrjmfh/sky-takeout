package com.sky.service.impl;

import com.sky.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;
    private static final String SHOP_STATUS_KEY = "SHOP_STATUS";

//  获取信息
    public Integer getShopStatus(){
        Integer status = redisTemplate.opsForValue().get(SHOP_STATUS_KEY);
        if (status == null) {
            status = 1; // 默认状态
            redisTemplate.opsForValue().set(SHOP_STATUS_KEY, status);
        }
        return status;
    }
//  保存信息
    public void updateShopStatus(Integer status){
        redisTemplate.opsForValue().set(SHOP_STATUS_KEY, status);
    }
}
