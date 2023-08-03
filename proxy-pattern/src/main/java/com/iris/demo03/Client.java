package com.iris.demo03;

public class Client {
    public static void main(String[] args) {
        // 真实角色
        Landlord landlord = new Landlord();
        // 代理角色 现在没有
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        // 通过调用程序处理角色，来处理我们要用的接口对象
        proxyInvocationHandler.setRent(landlord);
        // 这里返回的proxy就是动态生成的。
        Rent proxy = (Rent) proxyInvocationHandler.getProxy();
        proxy.rent();
    }
}
