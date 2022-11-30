package com.iris.dao;

public class UserDaoOracleImpl implements UserDao{
    public UserDaoOracleImpl(){
        System.out.println("UserDaoOracleImpl构造器");
    }
    @Override
    public void getUser() {
        System.out.println("默认获取用户的数据--Oracle");
    }
}
