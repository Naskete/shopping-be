package com.work.shopping.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Entity.User;
import com.work.shopping.Service.UserService;
import com.work.shopping.Utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class UserController  {
    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public SaResult findAll(){
       return new SaResult(200, "successful", userService.findAll());
    }



    /*
    * 将注册用户的信息保存到数据库，返回login界面
    * */
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


    @GetMapping("/user")
    public SaResult getUserByAccount(){
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        String id = StpUtil.getLoginId().toString();
        return new SaResult(200,"successful",userService.getUserByAccount(id));
    }

    @PostMapping("user/update")
    public SaResult updateUser(User user){
        if(!StpUtil.isLogin()){
            return SaResult.error("请登录");
        }
        userService.addUser(user);
        return SaResult.ok("修改成功");
    }

    @RequestMapping("user/logout")
    public SaResult deleteUser() {
        if (!StpUtil.isLogin()) {
            return SaResult.error("请登录");
        }
        String account = StpUtil.getLoginId().toString();
        userService.deleteUser(userService.getUserByAccount(account));
        return SaResult.ok(" 删除成功");
    }

    //
    @GetMapping("user/registerVip")
    public SaResult registerVip(){
        if (!StpUtil.isLogin()) {
            return SaResult.error("请登录");
        }
        String account = StpUtil.getLoginId().toString();
        User user = userService.getUserByAccount(account);
        user.setVip(1);
        userService.addUser(user);
        return  SaResult.ok("恭喜成功注册为会员");
    }

}
