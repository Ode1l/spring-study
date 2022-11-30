package com.iris.dao;

public class UserDaoImpl implements UserDao{
    public UserDaoImpl(){
        System.out.println("UserDaoImpl构造器");
    }
    @Override
    public void getUser() {
        System.out.println("默认获取用户的数据");
    }
}
