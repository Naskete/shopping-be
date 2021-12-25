package com.work.shopping.Dao;

import com.work.shopping.Entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShopDao extends JpaRepository<Shop, String> {
    @Query(value = "select * from shop where bussiness =?1 ", nativeQuery = true)
    Shop getShopByBussiness(String bussiness);
}
