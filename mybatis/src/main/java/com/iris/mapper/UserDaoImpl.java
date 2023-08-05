package com.iris.mapper;

import com.iris.po.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserMapper  {
    @Override
    public List<User> query() {
        return getSqlSession().getMapper(UserMapper.class).query();
    }
}
