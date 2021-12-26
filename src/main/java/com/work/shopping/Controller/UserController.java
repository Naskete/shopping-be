package com.work.shopping.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Entity.Shop;
import com.work.shopping.Entity.User;
import com.work.shopping.Service.ShopService;
import com.work.shopping.Service.ShoppingService;
import com.work.shopping.Service.UserService;
import com.work.shopping.Utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class UserController  {
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShoppingService shoppingService;


//    @GetMapping("/users")
//    public SaResult findAll() {
//        return new SaResult(200, "successful", userService.findAll());
//    }

    /**
     * @param account 账号
     * @param password 密码
     * @return
     */
    @PostMapping("/user/register")
    public SaResult registerUser(@RequestParam("account") String account, @RequestParam("password") String password){
        User user = userService.getUserByAccount(account);
        if(user != null){
            return SaResult.error("用户已存在，请直接登录");
        }
        password = new MD5Util().md5(password);
        System.out.println(password);
        user = new User(account, password);
        userService.addUser(user);
        return new SaResult(200,"注册成功",null);
    }


    /**
     * @param token token
     * @return user
     */
    @GetMapping("/user")
    public SaResult getUserByAccount(@RequestHeader("Authorization") String token){
        String id = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(id == null){
            return new SaResult(400, "请登录后查看", null);
        }
        return new SaResult(200,"successful",userService.getUserByAccount(id));
    }
//
//    @PostMapping("user/update")
//    public SaResult updateUser(User user){
//        if(!StpUtil.isLogin()){
//            return SaResult.error("请登录");
//        }
//        userService.addUser(user);
//        return SaResult.ok("修改成功");
//    }
//
<<<<<<< HEAD
    @PostMapping("deleteuser")
    public SaResult deleteUser(@RequestParam("account")String account) {
        if (!StpUtil.isLogin()) {
            return SaResult.error("请登录");
        }
        for (Shop s:shopService.getShopByBussiness(account)){
            shoppingService.offProduct(s.getShopName());
            shopService.deleteShop(s.getShopName());
        }
        shoppingService.deleteAccountByAccount(account);
        shoppingService.deleteShoppingCartByAccount(account);
        shoppingService.deleteOrderByAccount(account);
        userService.deleteUser(account);
        return SaResult.ok(" 删除成功");
    }
//
//    //
//    @GetMapping("user/registerVip")
//    public SaResult registerVip(){
//        if (!StpUtil.isLogin()) {
//            return SaResult.error("请登录");
//        }
//        String account = StpUtil.getLoginId().toString();
//        userService.registerVIP(account);
//        return  SaResult.ok("恭喜成功注册为会员");
//    }
=======

    @GetMapping("user/registerVip")
    public SaResult registerVip(@RequestHeader("Authorization") String token){
        String account = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(account == null){
            return new SaResult(400, "请登录后查看", null);
        }
        userService.registerVIP(account);
        return  SaResult.ok("恭喜成功注册为会员");
    }
>>>>>>> e024775296d9ef6b801d752d80e8369b650abbc9

}
