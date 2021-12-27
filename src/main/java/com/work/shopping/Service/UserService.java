package com.work.shopping.Service;

import com.work.shopping.Dao.UserDao;
import com.work.shopping.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    /** 通过账号获取用户信息
     * account 用户account
     * return User
     */
    public User getUserByAccount(String account){
        return userDao.getUserByAccount(account);
    }

    /**
     * 注册，添加用户
     */
    public void addUser(User user){
        userDao.save(user);
    }

    public void deleteUser(String account) {userDao.deleteUserByAccount(account); }

    public Collection<User> findAll() {
       return userDao.findAll();
    }

    public void beBusiness(String business){
        userDao.beBusiness(business);
    }

    public void registerVIP(String account){
        userDao.registerVIP(account);
    }
}
