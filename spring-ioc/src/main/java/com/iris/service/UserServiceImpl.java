package com.iris.service;

import com.iris.dao.UserDao;
import com.iris.dao.UserDaoImpl;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl(){
        System.out.println("UserServiceImpl构造器");
    }

    // 利用set控制动态实现值的注入
    @Override
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public void getUser() {
        userDao.getUser();
    }
}
