package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 营业状态管理
 */
@RestController
@RequestMapping("/admin/shop")
@Api(tags = "营业状态管理")
@Slf4j
public class AdminShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 获取营业状态
     */
    @GetMapping("/status")
    public Result<Integer> getShopStatus() {
        log.info("获取营业状态...");
        Integer status = shopService.getShopStatus();
        return Result.success(status);
    }

    /**
     * 修改营业状态
     */
    @PutMapping("/{status}")
    public Result<String> updateShopStatus(@PathVariable Integer status) {
        log.info("修改营业状态为: {}", status);
        shopService.updateShopStatus(status);
        return Result.success();
    }
}
