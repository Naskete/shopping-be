package com.work.shopping.Dao;

import com.work.shopping.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    @Transactional
    @Query(value = "select * from users where account = ?1", nativeQuery = true)
    User getUserByAccount(String account);
}
