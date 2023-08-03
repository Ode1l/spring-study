package com.iris.demo02;

import com.iris.demo04.ProxyInvocationHandler;

public class Client {
    public static void main(String[] args) {
        // 真实角色
        UserService userService = new UserServiceImpl();
        // 代理角色目前没有，生成一个助手
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        // 向助手扔一个类，助手会反射得到这个类实现的接口
        // 设置代理类，设置代理的对象
        pih.setTarget(userService);
        // 再根据设置的接口生成一个代理类
        // 动态生成代理类
        UserService userServiceProxy = (UserService) pih.getProxy();
        userServiceProxy.add();
    }
}
