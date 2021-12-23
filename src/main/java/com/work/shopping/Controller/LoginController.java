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
public class LoginController {
    @Autowired
    private MD5Util md5Util;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public SaResult login(@RequestParam("username") String account,
                          @RequestParam("password") String password){
//        System.out.println(account + "\t" + password);
        // 从数据库查询
        if(account.equals("")|| password.equals("")){
            return SaResult.error("用户名或密码不能为空");
        }
        User user = userService.getUserByAccount(account);
        String mdash = user.getPassword();
//        System.out.println("mdash = " + mdash);
        if(md5Util.checkPwd(password, mdash)){
            StpUtil.login(account);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败，用户名或密码错误");
    }

    @GetMapping("/logout")
    public SaResult logout(){
        StpUtil.logout();
        return SaResult.ok("登出成功");
    }
}
