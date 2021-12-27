package com.work.shopping.Dao;

import com.work.shopping.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    @Query(value = "select * from users where account = ?1", nativeQuery = true)
    User getUserByAccount(String account);
    @Modifying
    @Transactional
    @Query(value = "update users set grade = 2 where account = ?",nativeQuery = true)
    void beBusiness(String business);
    @Modifying
    @Transactional
    @Query(value = "update users set remain = ?1 where account = ?2",nativeQuery = true)
    void bill(float remain,String account);
    @Modifying
    @Transactional
    @Query(value = "delete from users where account = ?",nativeQuery = true)
    void deleteUserByAccount(String account);
    @Modifying
    @Transactional
    @Query(value = "update users set vip = true where account = ?",nativeQuery = true)
    void registerVIP(String account);
}
