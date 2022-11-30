package com.iris.dao;

public class UserDaoMysqlImpl implements UserDao{
    public UserDaoMysqlImpl(){
        System.out.println("UserDaoMysqlImpl构造器");
    }
    @Override
    public void getUser() {
        System.out.println("默认获取用户的数据--Mysql");
    }
}
