package com.iris.po;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;

public class Human {
    // 如果定义了 required = false，说明这个对象可以为null
    @Autowired(required = false)
    private Cat cat;
    // 如果同一属性bean过多，名字需要dog1 dog2 自动装配无法确定是哪一个时，可以使用 @Qualifier(value = "dog111") 来锁定

    // java自带了@Resource(name = "dog") 或 @Resource 效果略等同于@Autowired。java11后取消该该注解了。
    @Autowired
    @Qualifier(value = "dog")
    private Dog dog;
    public String name;

    public Cat getCat() {
        return cat;
    }

    public void setCat(@Nullable Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Human{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
