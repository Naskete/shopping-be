package com.work.shopping.Dao;

import com.work.shopping.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface OrderDao extends JpaRepository<Order, BigInteger> {
}
