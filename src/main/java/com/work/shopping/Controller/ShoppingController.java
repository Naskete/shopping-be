package com.work.shopping.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Entity.Product;
import com.work.shopping.Service.ShoppingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/purchase")
    public SaResult purchase(@RequestBody Product product){
        // 判断登录状态
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        // 获取当前用户account String productId,
        String account = StpUtil.getLoginId().toString();
        shoppingService.purchase(account, product);
        return SaResult.ok("ok");
    }
    @GetMapping("/shoppingcart")
    public SaResult getShoppingCart(){
        // 判断登录状态
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        // 获取当前用户account String productId,
        String account = StpUtil.getLoginId().toString();
        return new SaResult(200,"successful",shoppingService.getShoppingCart(account));
    }
    @PostMapping("/deletecart")
    public SaResult deleteProduct(@RequestParam("id")String id){
        shoppingService.deleteProduct(id);
        return SaResult.ok("ok");
    }
    @GetMapping("/comment/{productid}")
    public SaResult getComment(@PathVariable("productid")String productid){
        return new SaResult(200,"successful",shoppingService.getComment(productid));
    }
    @PostMapping("/addcomment")
    public SaResult addComment(@RequestParam("content")String content,@RequestParam("productid")String productid){
        // 判断登录状态
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        // 获取当前用户account String productId,
        String account = StpUtil.getLoginId().toString();
        shoppingService.addComment(account, content,productid);
        return SaResult.ok("ok");
    }
    @PostMapping("/getprice")
    public SaResult getPrice(@RequestParam("id")String[] id){
        // 判断登录状态
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        return new SaResult(200,"ok",shoppingService.getPrice(id));
    }
    @PostMapping("/bill")
    public SaResult bill(@RequestParam("id")String[] id,@RequestParam("des")String des){
        // 判断登录状态
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        // 获取当前用户account String productId,
        String account = StpUtil.getLoginId().toString();
        shoppingService.bill(id,account,des);
        return SaResult.ok("ok");
    }
    @GetMapping("/orders")
    public SaResult getOrders(){
        // 判断登录状态
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        // 获取当前用户account String productId,
        String account = StpUtil.getLoginId().toString();
        return new SaResult(200,"ok",shoppingService.getOrders(account));
    }
    @PostMapping("/order")
    public SaResult getOrderById(@RequestParam("id")String id){
        // 判断登录状态
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        // 获取当前用户account String productId,
        String account = StpUtil.getLoginId().toString();
        return new SaResult(200,"ok",shoppingService.getOrderById(id));
    }
}
