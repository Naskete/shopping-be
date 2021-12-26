package com.work.shopping.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Entity.Product;
import com.work.shopping.Entity.Shop;
import com.work.shopping.Entity.User;
import com.work.shopping.Service.ShopService;
import com.work.shopping.Service.ShoppingService;
import com.work.shopping.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
public class shopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingService shoppingService;


    @GetMapping("/shops")
    public SaResult findAll(@RequestHeader("Authorization") String token){
        String id = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(id == null){
            return new SaResult(400, "请登录后查看", null);
        }
        User usr = userService.getUserByAccount(id);
        if(usr.getGrade()!=3) {
            return new SaResult(403, "没有权限", null);
        }
        return new SaResult(200, "successful", shopService.findAll());
    }

    /**
     * 获取商家名称
     * @param token
     * @return
     */
    @GetMapping("/shop")
    public SaResult getShopByBussiness(@RequestHeader("Authorization") String token){
        // 判断登录状态
        String business = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(business == null){
            return new SaResult(400, "请登录后查看", null);
        }
        return new SaResult(200,"successful",shopService.getShopByBussiness(business));
    }


    /**
     * 注册商家
     * @param shopName 商家名称
     * @param token token
     * @return
     */
    @PostMapping("/Shop/register")
    public SaResult registerShop(@RequestParam("shopName") String shopName, @RequestHeader("Authorization") String token){
        String business = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(business == null){
            return new SaResult(400, "请登录后查看", null);
        }
        Shop shop =  new Shop();
        shop.setShopName(shopName);
        shop.setBusiness(business);
        shopService.addShop(shop);
        userService.beBusiness(business);
        return SaResult.ok("ok");
    }

    /**
     * 上传
     * @param name 商品名
     * @param shop 商家名
     * @param desc 描述
     * @param img 图片地址
     * @param price 价格
     * @return
     */
    @PostMapping("/upload")
    public SaResult uploadProduct(@RequestParam("name")String name, @RequestParam("shop")String shop, @RequestParam("description")String desc, @RequestParam("image")MultipartFile img, @RequestParam("price")String price, @RequestHeader("Authorization") String token){
        String account = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(account == null){
            return new SaResult(400, "请登录后查看", null);
        }
        if (img.isEmpty()||name.isEmpty()||shop.isEmpty()||desc.isEmpty()||price.isEmpty()) {
            return  new SaResult(400, "请输入完整信息", null);
        }
        float price2;
        try {
            price2 = Float.parseFloat(price);
        }catch (NumberFormatException e){
            return new SaResult(500,"请输入正确的价格",null);
        }
        try {
            byte[] bytes = img.getBytes();
            Path path = Paths.get("images/"+img.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            return new SaResult(500,"上传失败",e.getMessage());
        }
        Product product = new Product();
        product.setId(new BigInteger("0"));
        product.setName(name);
        product.setShop(shop);
        product.setStatus(true);
        product.setImg(img.getOriginalFilename());
        product.setPrice(price2);
        product.setDescription(desc);
        shoppingService.uploadProduct(product);
        return SaResult.ok("上传成功");
    }

    @PostMapping("/shop/delete")
    public SaResult deleteShop(@RequestParam("shopname")String shopname, @RequestHeader("Authorization") String token) {
        String business = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(business == null){
            return new SaResult(400, "请登录后查看", null);
        }
        shoppingService.offProduct(shopname);
        shopService.deleteShop(shopname);
        return SaResult.ok(" 删除成功");
    }
}
