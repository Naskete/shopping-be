package com.work.shopping.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.work.shopping.Dao.CommentDao;
import com.work.shopping.Dao.OrderDao;
import com.work.shopping.Dao.ProductDao;
import com.work.shopping.Dao.ShoppingCartDao;
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
    public JSONObject getShoppingCart(String id){
        JSONObject j = new JSONObject();
        for (ShoppingCart s:shoppingCartDao.findAllByAcount(id)){
            j.put(s.getId().toString(),JSON.parseObject(s.getData()));
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
    public void bill(String[] id,String account,String des){
        JSONObject j = new JSONObject();
        for (String s:id){
            j.put(s,JSON.parseObject(shoppingCartDao.findAllDataById(s)));
        }
        Order order = new Order();
        order.setAcount(account);
        order.setData(JSON.toJSONString(j));
        order.setId(new BigInteger("0"));
        order.setDestination(des);
        orderDao.save(order);
        for (String s:id){
            deleteProduct(s);
        }

    }
    public JSONObject getOrders(String account){
        JSONObject j = new JSONObject();
        for (Order o:orderDao.findAllByAcount(account)){
            j.put(String.valueOf(o.getId()),JSON.parseObject(o.getData()));
        }
        return j;
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
        comment.setAcount(userId);
        comment.setId("0");
        comment.setProductid(productid);
        commentDao.save(comment);
    }
}
