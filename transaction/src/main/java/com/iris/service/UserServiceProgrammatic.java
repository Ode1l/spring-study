package com.iris.service;

import com.iris.mapper.UserMapper;
import com.iris.po.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceProgrammatic implements UserService{
    private final PlatformTransactionManager transactionManager;
    public UserServiceProgrammatic(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public void invoke() {
        TransactionStatus txStatus =
                transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            User user = new User(3,"橙猫猫","156");
            userMapper.add(user);
            userMapper.delete(3);
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            throw e;
        }
        transactionManager.commit(txStatus);
    }
}
