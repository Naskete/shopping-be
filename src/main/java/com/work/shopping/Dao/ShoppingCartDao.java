package com.work.shopping.Dao;

import com.work.shopping.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, BigInteger> {
    @Query(value = "select data from shoppingcart where acount = ?",nativeQuery = true)
    List<String> findAllByAcount(String id);
    @Modifying
    @Transactional
    @Query(value = "delete from shoppingcart where id = ?",nativeQuery = true)
    void deleteShoppingCartById(String id);
}
