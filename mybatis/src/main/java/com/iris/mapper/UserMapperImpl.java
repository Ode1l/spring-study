package com.iris.mapper;

import com.iris.po.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper {
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> query() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.query();
    }
}
