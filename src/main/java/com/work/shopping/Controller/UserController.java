package com.work.shopping.Controller;

import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Entity.User;
import com.work.shopping.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin
@RestController
public class UserController  {
    @Autowired
    private UserService userService;

    /*
    * 将所有user信息返回到list界面下
    * */
    @RequestMapping("/users")
    public String list(Model model){
        Collection<User> users = userService.getAll();
        model.addAttribute("users",users);
        return "user/list";
    }

    /*
    * 将注册用户的信息保存到数据库，返回login界面
    * */
    @PostMapping("/registerUser")
    public String registerUser(User user){
        userService.addUser(user);
        return "redirect:/login";
    }

    /*
    * 前往修改用户信息的界面user/add
    * */
    @GetMapping("/user/{account}")
    public String toUpdateUser(@PathVariable("account") String account, Model model){
        // 查出原来的数据
        User user = userService.getUserByAccount(account);
        model.addAttribute("user",user);
        return "user/add";
    }

    @PostMapping("updateUser")
    public SaResult updateUser(User user){
        userService.addUser(user);
        return SaResult.ok("修改成功");
    }






}
