package com.work.shopping.Dao;

import com.work.shopping.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
@Repository
public interface CommentDao extends JpaRepository<Comment, BigInteger> {
    @Query(value = "select * from comment where productid = ?",nativeQuery = true)
    List<Comment> findByProductId(String productid);
    @Modifying
    @Transactional
    @Query(value = "update comment set account = null where account = ?",nativeQuery = true)
    void deleteAccountByAccount(String account);
}
