package com.iris.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 不再写静态代理，使用动态代理程序自动生成代理
public class ProxyInvocationHandler implements InvocationHandler {

    // 被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    // 生成得到代理类(中介)
    // 通过反射生成实现rent接口的类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }

    // 主程序动态生成代理类(中介)后，proxy.rent();代码会被下面的invoke改写成下面的代码。
    // 处理代理实例并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 动态代理 是使用反射机制实现的
        guide();
        // 反射的invoke执行rent方法
        Object result = method.invoke(rent, args);
        charge();
        return result;
    }

    public void guide() {
        System.out.println("Guided tour of house");
    }
    public void charge(){
        // 收中介费
        System.out.println("Collect intermediary fees");
    }
}
