package com.work.shopping.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.work.shopping.Dao.CommentDao;
import com.work.shopping.Dao.ProductDao;
import com.work.shopping.Dao.ShoppingCartDao;
import com.work.shopping.Entity.Comment;
import com.work.shopping.Entity.Product;
import com.work.shopping.Entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
@Service
public class ShoppingService {
    /**
     *  获取商品列表（主页展示）
     */
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private CommentDao commentDao;

    public List<Product> getProducts(){
        return productDao.findAll();
    }

    /**
     * 通过商品id获取商品
     * @param id 商品id
     * @return 商品对象 Product
     */
    public Product getProductById(String id){
        return productDao.getById(id);
    }

    /**
     * 添加商品至购物车
     * @param userId 用户id
     * @param product
     */
    public void purchase(String userId, Product product){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(new BigInteger("0"));
        shoppingCart.setAcount(userId);
        shoppingCart.setData(JSON.toJSONString(product));
        shoppingCartDao.save(shoppingCart);
    }
    /**
     * 获取购物车内容
     * @param id 用户id
     * @return 商品列表
     */
    public List<JSONObject> getShoppingCart(String id){
        List<JSONObject> j = new ArrayList<JSONObject>();
        for (String s:shoppingCartDao.findAllByAcount(id)){
            j.add(JSON.parseObject(s));
        }
        return j;
    }

    /**
     * 从购物车中删除商品
     * @param id 商品id
     */
    public void deleteProduct(String id){
        shoppingCartDao.deleteShoppingCartById(id);
    }

    /**
     * 结算（待定）
     */
    public void bill(){

    }

    /**
     * 获取某商品的评论
     * @param productid 商品id
     * @return 评论列表
     */
    public List<Comment> getComment(String productid){
        return commentDao.findByProductId(productid);
    }
    public void addComment(String userId, String content,String productid){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAcount(userId);
        comment.setId("0");
        comment.setProductid(productid);
        commentDao.save(comment);
    }
}
