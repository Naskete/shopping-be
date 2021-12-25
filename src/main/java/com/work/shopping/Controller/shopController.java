package com.work.shopping.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Entity.Shop;
import com.work.shopping.Service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class shopController {
    @Autowired
    private ShopService shopService;

    @RequestMapping("/shops")
    public SaResult findAll(){
        return new SaResult(200, "successful", shopService.findAll());
    }

    @PostMapping("/Shop/register")
    public SaResult rigisterShop(@RequestParam("shopName") String shopName){
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        String bussiness = StpUtil.getLoginId().toString();
        Shop shop =  new Shop();
        shop.setShopName(shopName);
        shop.setBussiness(bussiness);
        shopService.addShop(shop);
        return SaResult.ok("注册成功");
    }

    @PostMapping("shop/updateName")
    public SaResult updateShopName(@RequestParam("shopName") String shopName){
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        String bussiness = StpUtil.getLoginId().toString();
        Shop shop = shopService.getShopByBussiness(bussiness);
        shop.setShopName(shopName);
        shopService.addShop(shop);
        return SaResult.ok("修改成功");
    }

    @RequestMapping("shop/logout")
    public SaResult deleteShop() {
        if (!StpUtil.isLogin()) {
            return SaResult.error("请登录");
        }
        String bussiness = StpUtil.getLoginId().toString();
        shopService.deleteShop(shopService.getShopByBussiness(bussiness));
        return SaResult.ok(" 删除成功");
    }
}