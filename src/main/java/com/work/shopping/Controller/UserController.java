package com.work.shopping.Controller;

import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class UserController  {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public SaResult getUserByAccount(@PathVariable("id") String id){
        return new SaResult(200,"successful", userService.getUserByAccount(id));
    }
}
