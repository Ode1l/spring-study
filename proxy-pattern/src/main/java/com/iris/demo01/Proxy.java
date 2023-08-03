package com.iris.demo01;

// 代理 or 中介
public class Proxy implements Rent{
    // 使用组合方式 先获得房东信息
    private Landlord landlord;
    // 中介推荐房源
    public Proxy() {
        this.landlord = new Landlord();
    }
    // 看指定房源
    public Proxy(Landlord landlord) {
        this.landlord = landlord;
    }

    @Override
    public void rent() {
        guide();
        landlord.rent();
        sign();
        charge();
    }
    public void guide(){
        System.out.println("Guided tour of house");
    }
    public void charge(){
        // 收中介费
        System.out.println("Collect intermediary fees");
    }
    public void sign(){
        // 签合同
        System.out.println("Sign a contract");
    }
}
