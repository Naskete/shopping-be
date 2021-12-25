package com.work.shopping.Service;

import com.work.shopping.Dao.ShopDao;
import com.work.shopping.Entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShopService {
    @Autowired
    private ShopDao shopDao;

    public void addShop(Shop shop) {shopDao.save(shop);}

    public Collection<Shop> findAll() {return shopDao.findAll();}

    public void deleteShop(Shop shop) {shopDao.delete(shop);}

    public Shop getShopByBussiness(String bussiness){
        return shopDao.getShopByBussiness(bussiness);
    }
}
