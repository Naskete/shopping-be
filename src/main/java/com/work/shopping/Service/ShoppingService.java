package com.work.shopping.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.work.shopping.Dao.*;
import com.work.shopping.Entity.Comment;
import com.work.shopping.Entity.Order;
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
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

    public List<Product> getProducts(){
        return productDao.findAll();
    }
    public void uploadProduct(Product product){
        productDao.save(product);
    }
    public void offProduct(String shop){
        productDao.offProduct(shop);
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
        shoppingCart.setAccount(userId);
        shoppingCart.setData(JSON.toJSONString(product));
        shoppingCartDao.save(shoppingCart);
    }
    /**
     * 获取购物车内容
     * @param id 用户id
     * @return 商品列表
     */
    public JSONObject getShoppingCart(String id){
        JSONObject j = new JSONObject();
        for (ShoppingCart s:shoppingCartDao.findAllByAccount(id)){
            j.put(s.getId().toString(),JSON.parseObject(s.getData()));
        }
        return j;
    }
    public void deleteShoppingCartByAccount(String account){
        shoppingCartDao.deleteAllByAccount(account);
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
    public void bill(String[] id,String account){
        JSONObject j = new JSONObject();
        for (String s:id){
            j.put(s,JSON.parseObject(shoppingCartDao.findAllDataById(s)));
        }
        Order order = new Order();
        order.setAccount(account);
        order.setData(JSON.toJSONString(j));
        order.setId(new BigInteger("0"));
        order.setDestination("南昌大学");
        orderDao.save(order);
        for (String s:id){
            deleteProduct(s);
        }
        userDao.bill(0,account);
    }
    public JSONObject getOrders(String account){
        JSONObject j = new JSONObject();
        for (Order o:orderDao.findAllByAccount(account)){
            j.put(String.valueOf(o.getId()),JSON.parseObject(o.getData()));
        }
        return j;
    }
    public void deleteOrderByAccount(String account){
        orderDao.deleteAllByAccount(account);
    }
    public JSONObject getOrderById(String id){
        JSONObject j = new JSONObject();
        j.put("id",orderDao.findById(id).getId());
        j.put("data",JSON.parseObject(orderDao.findById(id).getData()));
        j.put("des",orderDao.findById(id).getDestination());
        return j;
    }
    public List<JSONObject> getPrice(String[] id){
        List<JSONObject> j = new ArrayList<>();
        for (String s:id){
            j.add(JSON.parseObject(shoppingCartDao.findAllDataById(s)));
        }
        return j;
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
        comment.setAccount(userId);
        comment.setId("0");
        comment.setProductid(productid);
        commentDao.save(comment);
    }
    public void deleteAccountByAccount(String account){
        commentDao.deleteAccountByAccount(account);
    }
}
