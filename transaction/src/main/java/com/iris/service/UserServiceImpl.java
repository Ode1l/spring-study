package com.iris.service;

import com.iris.mapper.UserMapper;
import com.iris.po.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void invoke() {
        User user = new User(3,"橙猫猫","156");
        userMapper.add(user);
        userMapper.delete(3);
    }
}
