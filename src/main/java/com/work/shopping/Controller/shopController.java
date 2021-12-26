package com.work.shopping.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Entity.Shop;
import com.work.shopping.Entity.User;
import com.work.shopping.Service.ShopService;
import com.work.shopping.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class shopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;

    @GetMapping("/shops")
    public SaResult findAll(){
        return new SaResult(200, "successful", shopService.findAll());
    }

    @GetMapping("/shop")
    public SaResult getShopByBussiness(){
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        String bussiness = StpUtil.getLoginId().toString();
        return new SaResult(200,"successful",shopService.getShopByBussiness(bussiness));
    }

    @PostMapping("/Shop/register")
    public SaResult registerShop(@RequestParam("shopName") String shopName){
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        String bussiness = StpUtil.getLoginId().toString();
        Shop shop =  new Shop();
        shop.setShopName(shopName);
        shop.setBussiness(bussiness);
        shopService.addShop(shop);
        userService.beBussiness(bussiness);
        return SaResult.ok("ok");
    }

    @PostMapping("shop/logout")
    public SaResult deleteShop(@RequestParam("shopname")String shopname) {
        if (!StpUtil.isLogin()) {
            return SaResult.error("请登录");
        }
        shopService.deleteShop(shopname);
        return SaResult.ok(" 删除成功");
    }
}
