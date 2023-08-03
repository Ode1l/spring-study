package com.iris.demo01;

// 房东
public class Landlord implements Rent{
    @Override
    public void rent() {
        // 出租房产
        System.out.println("Rent out properties");
    }
}
