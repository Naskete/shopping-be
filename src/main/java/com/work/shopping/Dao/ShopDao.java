package com.work.shopping.Dao;

import com.work.shopping.Entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShopDao extends JpaRepository<Shop, String> {
    @Query(value = "select * from shop where business =?1 ", nativeQuery = true)
    List<Shop> getShopByBussiness(String business);
    @Modifying
    @Transactional
    @Query(value = "delete from shop where shop_name = ?",nativeQuery = true)
    void deleteShopByShopName(String shopname);
}
