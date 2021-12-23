package com.work.shopping.Controller;

import cn.dev33.satoken.util.SaResult;
import com.work.shopping.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
public class ShoppingController {
    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("/products")
    public SaResult findAll(){return new SaResult(200,"successful",shoppingService.getProducts());}

    @GetMapping("/product/{id}")
    public SaResult getProductByID(@PathVariable("id")String id){
        return new SaResult(200,"successful",shoppingService.getProductById(id));
    }
}
