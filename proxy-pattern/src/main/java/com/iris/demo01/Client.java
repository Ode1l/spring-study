package com.iris.demo01;

// 客户
public class Client {
    // 租一间房子
    public static void main(String[] args) {
        // Landlord landlord = new Landlord();
        // landlord.rent();
        // Proxy proxy = new Proxy(new Landlord());
        Proxy proxy = new Proxy();
        proxy.rent();
    }
}
