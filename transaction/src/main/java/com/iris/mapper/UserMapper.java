package com.iris.mapper;

import com.iris.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<User> query();
    public boolean add(User user);
    public boolean delete(@Param("id") int id);
}
