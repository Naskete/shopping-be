package com.work.shopping.Dao;

import com.work.shopping.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, BigInteger> {
    @Query(value = "select * from orders where account = ?",nativeQuery = true)
    List<Order> findAllByAccount(String account);
    @Query(value = "select * from orders where id = ?",nativeQuery = true)
    Order findById(String id);
}
