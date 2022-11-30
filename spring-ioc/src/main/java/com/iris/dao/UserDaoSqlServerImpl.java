package com.iris.dao;

public class UserDaoSqlServerImpl implements UserDao{
    public UserDaoSqlServerImpl(){
        System.out.println("UserDaoSqlServerImpl构造器");
    }
    @Override
    public void getUser() {
        System.out.println("默认获取用户的数据--SqlServer");
    }
}
