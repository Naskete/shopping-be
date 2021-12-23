package com.work.shopping.Service;

import com.work.shopping.Dao.ProductDao;
import com.work.shopping.Entity.Comment;
import com.work.shopping.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public class ShoppingService {
    /**
     *  获取商品列表（主页展示）
     */
    @Autowired
    private ProductDao productDao;

    public List<Product> getProducts(){
        return productDao.findAll();
    }

    /**
     * 通过商品id获取商品
     * @param id 商品id
     * @return 商品对象 Product
     */
    public Product getProductById(String id){
        return productDao.getById(new BigInteger(id));
    }

    /**
     * 添加商品至购物车
     * @param userId 用户id
     * @param id 商品id
     */
    public void purchase(String userId, String id){

    }

    /**
     * 获取购物车内容
     * @param id 用户id
     * @return 商品列表
     */
    public List<Product> getShoppingCart(String id){
        return null;
    }

    /**
     * 从购物车中删除商品
     * @param userId 用户id
     * @param id 商品id
     */
    public void deleteProduct(String userId, String id){

    }

    /**
     * 结算（待定）
     */
    public void bill(){

    }

    /**
     * 获取某商品的评论
     * @param id 商品id
     * @return 评论列表
     */
    public List<Comment> getComment(String id){
        return null;
    }
}
