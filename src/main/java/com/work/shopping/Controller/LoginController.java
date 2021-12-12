package com.work.shopping.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private MD5Util md5Util;

    @PostMapping("/login")
    public SaResult login(@RequestParam("username") String username,
                          @RequestParam("password") String password){
        // 从数据库查询
        String mdash = "ash";
        if(md5Util.checkPwd(password, mdash)){
            StpUtil.login(username);
            return SaResult.ok("登录成功");
        }
        return SaResult.ok("登录失败");
    }

    @GetMapping("/logout")
    public SaResult logout(){
        StpUtil.logout();
        return SaResult.ok("登出成功");
    }
}
