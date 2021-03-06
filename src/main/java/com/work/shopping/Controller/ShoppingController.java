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
    public SaResult findAll(){
        return new SaResult(200,"successful",shoppingService.getProducts());
    }

    /**
     *
     * @param id product id
     * @return product
     */
    @GetMapping("/product/{id}")
    public SaResult getProductByID(@PathVariable("id") String id){
        return new SaResult(200,"successful",shoppingService.getProductById(id));
    }

    /** 加入购物车
     * purchase
     * @param product
     * @return ok
     */
    @PostMapping("/purchase")
    public SaResult purchase(@RequestBody Product product, @RequestHeader("Authorization") String token){
        // 判断登录状态
        String id = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(id == null){
            return new SaResult(400, "请登录后查看", null);
        }
        shoppingService.purchase(id, product);
        return SaResult.ok("ok");
    }

    @GetMapping("/shoppingcart")
    public SaResult getShoppingCart(@RequestHeader("Authorization") String token){
        // 判断登录状态
        String account = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(account == null){
            return new SaResult(400, "请登录后查看", null);
        }
        return new SaResult(200,"successful",shoppingService.getShoppingCart(account));
    }

    /**
     * 删除购物车商品
     * @param id ID
     * @return
     */
    @PostMapping("/deletecart")
    public SaResult deleteProduct(@RequestParam("id")String id){
        shoppingService.deleteProduct(id);
        return SaResult.ok("ok");
    }

    /**
     *  get comment of product
     * @param id product id
     * @return List<comment>
     */
    @GetMapping("/comment/{id}")
    public SaResult getComment(@PathVariable("id")String id){
        return new SaResult(200,"successful",shoppingService.getComment(id));
    }


    @PostMapping("/addcomment")
    public SaResult addComment(@RequestParam("content")String content,@RequestParam("productid")String productid, @RequestHeader("Authorization") String token){
        String account = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(account == null){
            return new SaResult(400, "请登录后查看", null);
        }
        System.out.println();
        shoppingService.addComment(account, content,productid);
        return SaResult.ok("ok");
    }

    @PostMapping("/bill")
    public SaResult bill(@RequestParam("id") String id, @RequestHeader("Authorization") String token){
        // 判断登录状态
        String account = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(account == null){
            return new SaResult(400, "请登录后查看", null);
        }
        String[] ids = id.split(",");
        shoppingService.bill(ids,account);
        return SaResult.ok("ok");
    }

    @GetMapping("/orders")
    public SaResult getOrders(@RequestHeader("Authorization") String token){
        // 判断登录状态
        String account = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(account == null){
            return new SaResult(400, "请登录后查看", null);
        };
        return new SaResult(200,"ok",shoppingService.getOrders(account));
    }

}
