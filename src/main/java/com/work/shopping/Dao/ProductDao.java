package com.work.shopping.Dao;

import com.work.shopping.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductDao extends JpaRepository<Product, BigInteger> {
}
