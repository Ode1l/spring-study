package com.iris.service;

import com.iris.dao.UserDao;

public interface UserService {
    public void setUserDao(UserDao userDao);
    public void getUser();
}
