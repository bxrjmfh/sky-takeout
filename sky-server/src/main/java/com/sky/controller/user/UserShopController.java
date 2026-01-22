package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/shop")
@Api(tags = "用户-营业状态")
@Slf4j
public class UserShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 获取营业状态
     */
    @GetMapping("/status")
    public Result<Integer> getShopStatus() {
        log.info("用户端-获取营业状态...");
        Integer status = shopService.getShopStatus();
        return Result.success(status);
    }
}