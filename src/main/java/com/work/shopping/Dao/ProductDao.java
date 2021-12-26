package com.work.shopping.Dao;

import com.work.shopping.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, BigInteger> {
    @Query(value = "select * from products where id = ?",nativeQuery = true)
    Product getById(String id);
    @Query(value = "select * from products where status = true ",nativeQuery = true)
    List<Product> findAll();
}
