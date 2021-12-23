package com.work.shopping.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
@CrossOrigin
@RestController
public class ShoppingController {
    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("/products")
    public SaResult findAll(){return new SaResult(200,"successful",shoppingService.getProducts());}

    @GetMapping("/product/{id}")
    public SaResult getProductByID(@PathVariable("id") String id){
        return new SaResult(200,"successful",shoppingService.getProductById(id));
    }

    @PostMapping("/puschase")
    public SaResult purchase(String productId){
        // 判断登录状态
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        // 获取当前用户account
        String account = StpUtil.getLoginId().toString();
        System.out.println(account);
        shoppingService.purchase(account, productId);
        return SaResult.ok("ok");
    }
}
