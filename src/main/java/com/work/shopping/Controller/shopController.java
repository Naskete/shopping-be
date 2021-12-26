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
    public SaResult findAll(){
        return new SaResult(200, "successful", shopService.findAll());
    }

    @GetMapping("/shop")
    public SaResult getShopByBussiness(){
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        String business = StpUtil.getLoginId().toString();
        return new SaResult(200,"successful",shopService.getShopByBussiness(business));
    }

    @PostMapping("/shop/register")
    public SaResult registerShop(@RequestParam("shopName") String shopName){
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        String business = StpUtil.getLoginId().toString();
        Shop shop =  new Shop();
        shop.setShopName(shopName);
        shop.setBusiness(business);
        shopService.addShop(shop);
        userService.beBusiness(business);
        return SaResult.ok("ok");
    }
    @PostMapping("upload")
    public SaResult uploadProduct(@RequestParam("name")String name, @RequestParam("shop")String shop, @RequestParam("description")String desc, @RequestParam("image")MultipartFile img, @RequestParam("price")String price){
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        if (img.isEmpty()||name.isEmpty()||shop.isEmpty()||desc.isEmpty()||price.isEmpty()) {
            return SaResult.error("请输入完整信息");
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
    @PostMapping("test")
    public SaResult test(@RequestParam("a")String a)throws Exception{
        return null;
    }
    @PostMapping("shop/logout")
    public SaResult deleteShop(@RequestParam("shopname")String shopname) {
        if (!StpUtil.isLogin()) {
            return SaResult.error("请登录");
        }
        shoppingService.offProduct(shopname);
        shopService.deleteShop(shopname);
        return SaResult.ok(" 删除成功");
    }
}
