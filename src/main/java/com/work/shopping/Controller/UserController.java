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


    @GetMapping("/users")
    public SaResult findAll(@RequestHeader("Authorization") String token) {
        String id = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(id == null){
            return new SaResult(400, "请登录后查看", null);
        }
        User usr = userService.getUserByAccount(id);
        if(usr.getGrade()!=3) {
            return new SaResult(403, "没有权限", null);
        }
        return new SaResult(200, "successful", userService.findAll());
    }

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
        User usr = userService.getUserByAccount(id);
        return new SaResult(200,"successful",userService.getUserByAccount(id));
    }

    @PostMapping("/deleteuser")
    public SaResult deleteUser(@RequestParam("account")String account, @RequestHeader("Authorization") String token) {
        String id = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(id == null){
            return new SaResult(400, "请登录后查看", null);
        }
        User user = userService.getUserByAccount(id);
        if(user.getGrade() != 3){
            return new SaResult(403, "没有权限", null);
        }
        for (Shop s:shopService.getShopByBussiness(account)){
            shoppingService.offProduct(s.getShopName());
            shopService.deleteShop(s.getShopName(), id);
        }
        shoppingService.deleteAccountByAccount(account);
        shoppingService.deleteShoppingCartByAccount(account);
        shoppingService.deleteOrderByAccount(account);
        userService.deleteUser(account);
        return SaResult.ok(" 删除成功");
    }

    @GetMapping("/user/registerVip")
    public SaResult registerVip(@RequestHeader("Authorization") String token){
        String account = (String) StpUtil.getLoginIdByToken(token);
        // 未登录
        if(account == null){
            return new SaResult(400, "请登录后查看", null);
        }
        userService.registerVIP(account);
        return  SaResult.ok("恭喜成功注册为会员");
    }

}
