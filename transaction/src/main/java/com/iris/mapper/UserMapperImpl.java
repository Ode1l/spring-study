package com.iris.mapper;

import com.iris.po.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public List<User> query() {
        return getSqlSession().getMapper(UserMapper.class).query();
    }

    @Override
    public boolean add(User user) {
        return getSqlSession().getMapper(UserMapper.class).add(user);
    }

    @Override
    public boolean delete(int id) {
        return getSqlSession().getMapper(UserMapper.class).delete(id);
    }
}
