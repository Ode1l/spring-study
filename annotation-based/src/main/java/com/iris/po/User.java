package com.iris.po;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 注解效果等价于 <bean id="user" class="com.iris.po.User"/>
// Component 组件
@Component
@Scope("prototype")
public class User {
    // 相当于<bean><property name = "Name" value = "Ode1l" /></bean>
    // @Value 也可以写在set方法上。
    @Value("Ode1l")
    public String Name;
}
