package com.work.shopping.Dao;

import com.work.shopping.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, BigInteger> {
    @Query(value = "select * from shoppingcart where acount = ?",nativeQuery = true)
    List<ShoppingCart> findAllByAcount(String id);
}
