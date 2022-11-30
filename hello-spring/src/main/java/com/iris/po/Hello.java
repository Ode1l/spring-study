package com.iris.po;

public class Hello {
    public String str;

    public Hello(String str) {
        this.str = str;
        System.out.println("进入有参构造器");
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "str='" + str + '\'' +
                '}';
    }
}
