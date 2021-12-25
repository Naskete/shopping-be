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
        User user = userService.getUserByAccount(account);
        if (user==null){
            return new SaResult(400, "用户不存在，请先注册", null);
        }
        String mdash = user.getPassword();
        if(md5Util.checkPwd(password, mdash)){
            StpUtil.login(account);
            String token = StpUtil.getTokenInfo().tokenValue;
            return new SaResult(200, "登录成功", token);
        }
        return SaResult.error("登录失败，用户名或密码错误");
    }

    @GetMapping("/logout")
    public SaResult logout(){
        StpUtil.logout();
        return SaResult.ok("登出成功");
    }
}
