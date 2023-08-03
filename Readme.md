# 控制反转思想IoC

在之前的业务中，用户的需求可能会影响业务代码，我们需要根据用户的需求修改源代码，如果程序代码量十分庞大，修改一次的成本代价十分昂贵。

eg: 如果最开始使用mysql数据库，后续又改成oracle数据库，对于系统来说需要重写Dao DaoImpl，以及Service和ServiceImpl中的private dao。

使用一个Set接口实现，已经发生了革命性的变化。

```java
    private UserDao userDao;
    // 利用set控制动态实现值的注入
    @Override
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
```

之前，程序是主动创建对象，控制权在程序员手上。使用set注入之后，程序不再具有主动性，而是变成被动的接收对象。

这种思想从本质上解决了问题，程序员不再管理对象的创建new，系统耦合性大大降低。可以更加专注在业务的实现上。 

### IoC本质

所谓的控制反转，是只获得依赖对象的方式反转了。 这是IoC的原型prototype，但不是完整的IoC。

控制反转，是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式（自动装配）。

在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection，DI）。 DI 也是 IoC 的一种是实现方法。

## DI依赖注入



### 构造器注入

仅仅是一个构造器，功能受限制
```xml
<beans>
    <bean id="hello" class="com.iris.po.Hello">
        <constructor-arg name="str" value="Spring"/>
    </bean>
    <bean id="StringS" class="com.iris.po.Hello">
        <constructor-arg name="str" value=""/>
    </bean>
</beans>
```

### set注入（重点）

依赖bean对象创建依赖于spring容器

bean对象中的所有属性，由容器来注入

```xml
<beans>
    <bean id="address" class="com.iris.po.Address">
        <property name="adress" value="长沙" />
    </bean>
    <bean id="student" class="com.iris.po.Student">
        <!-- 第一种：直接注入基本类型值 -->
        <property name="name" value="iris"/>
        <!-- 第二种：bean注入，引用注入 -->
        <property name="address" ref="address"/>
        <!-- 第三种：数组Array注入 -->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>西游记</value>
                <value>水浒传</value>
                <value>三国演义</value>
            </array>
        </property>
        <!-- 第四种：列表List注入 -->
        <property name="hobbys">
            <list>
                <value>唱歌</value>
                <value>跳舞</value>
                <value>rap</value>
                <value>篮球</value>
            </list>
        </property>
        <!-- 第五种：map注入 -->
        <property name="cards">
            <map>
                <entry key="身份证" value="4104021568168"/>
                <entry key="银行卡" value="4152548168"/>
                <entry key="学生证" value="6816854"/>
                <entry key="VISA卡" value="410458168"/>
            </map>
        </property>
        <!-- 第六种：Set注入 -->
        <property name="games">
            <set>
                <value>LOL</value>
                <value>Dota2</value>
                <value>war thunder</value>
            </set>
        </property>
        <!-- 第七种：null注入 -->
        <property name="wife">
                <null/>
        </property>
        <!-- 第八种：properties注入 -->
        <property name="info">
            <props>
                <prop key="学号">20180523</prop>
                <prop key="姓名">iris</prop>
                <prop key="性别">男</prop>
            </props>
        </property>
    </bean>
</beans>
```

### 拓展方式注入

p命名空间，p代表properties， c命名空间，c代表constructor。可使用标签进行便捷操作。本质上与上面两者并无区别。

仅需使用前加入一行xml约束。

```xml
<beans>
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:c="http://www.springframework.org/schema/c"
</beans>
```

## bean作用域

使用scope标签控制

```xml
<bean id="user" class="com.iris.po.User" p:age="18" p:name="iris" scope="singleton" />
```

singleton: 单例模式（spring默认）。只生成一个bean，所有调用都会只调用这一个bean。

prototype: 原型模式。每一次调用都会生成一个新对象。

其余模式有 request，session，application，websocket。这些模式用于web中。

## bean自动装配

spring框架自身会自动搜索上下文进行装配。

spring有三种装配方式。

1. xml记录的配置。
2. 使用java代码配置。
3. 隐式自动装配。（重要常用）

### 自动装配方式
1. byName 
```xml
<beans>
    <bean id="dog" class="com.iris.po.Dog"/>
    <bean id="cat" class="com.iris.po.Cat"/>
    <bean id="human" class="com.iris.po.Human" autowire="byName">
        <property name="name" value="iris"/>
    </bean>
</beans>
```

byName: 通过bean的id和setCat中set后的名字一一对应绑定

2. byType

```xml
<beans>
    <bean class="com.iris.po.Dog"/>
    <bean class="com.iris.po.Cat"/>
    <bean id="human" class="com.iris.po.Human" autowire="byType">
        <property name="name" value="iris"/>
    </bean>
</beans>
```

byType: 通过bean的类型(class)和setXxx中类型(class)一一对应绑定。
但是存在缺陷，因为一个类型可能出现多个bean，此时无法一一对应。
优点是byType甚至可以不用写bean的id。

### 使用注解自动装配

JDK1.5  Spring2.5以后支持注解

The introduction of annotation-based configuration raised the question of whether this approach is “better” than XML. 

使用注解需要: 
1. 导入约束

导入context约束
```text
    xmlns:context="http://www.springframework.org/schema/context"
```

2. 配置注解支持 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

#### @AutoWired

直接在属性上使用，也可以在set方法上使用。使用AutoWired后，可以省略set方法。注解其实是使用反射实现byname方法

@AutoWired(required = false) 使用required = false可使属性对象为空值且不报错。

@Qualifier(value = "dog11") 如果出现名称重复情况，可使用此标签重新byname定位。

set方法中的字段可以使用@Nullable注解，使方法值可以为null。不会报错。
public void setCat(@Nullable Cat cat)

#### @Resource

java自带了@Resource标签，作用与Autowired相同
@Resource(name = "dog")

#### 区别

1. Autowired 默认byType。 resource默认byName，找不到通过byType，如果还找不到，报错。

## 注解完成所有功能

### @Component

放在类上，使该类被spring直接注册成bean，name为小写。

### @Value

放在属性上或者set方法上，直接赋值 @Value("Ode1l")

### @Component衍生注解

dao --@Repository
service --@Service
controller --@Controller
po --@Component

MVC不同层级对应不同注解，但是实质上都是@Component衍生出来的。

### @Scope(prototype)

放在类上，用来控制作用域，可用单例，实例等。

##XML对比注解

XML功能更全面，维护相对复杂。注解更便捷，但功能简单。

一般结合使用方式: XML用来管理bean。注解完成属性注入。

## 完全使用java方式配置类

使用java方式配置，需要创建一个config类，一般创建config包，包下创建SpringConfig类，

```java
@Configuration
public class SpringConfig {
    @Bean
    public User getUser(){
        return new User();
    }
}
```

类用@Configuration标记为SpringConfig。类中通过@Bean return new User();该方法触发，效果等同于在XML中注册。

同时也可以使用@ComponentScan("com.iris")包扫描器，记得在在需要注册的类上@Component注册。

```java
@Configuration
@ComponentScan("com.iris")
public class SpringConfig {
}

@Component
public class User {
    @Value("Ode1l")
    public String Name;
}
```

java配置方式与注解配合使用是目前最常见的方式。

## 代理模式

SpringAOP(面向切片)底层"实现"，就是代理模式。

1. 静态代理
2. 动态代理

### 静态代理

优点:
+ 可以使真实角色(房东)操作更加纯粹(代码干净整洁)，不用去关注公共业务(不用管如何匹配客户，看房等杂活)
+ 公共业务交给代理角色实现，实现业务分工，代码抽象复用
+ 公共业务发生扩展时候，方便集中管理

缺点:
+ 一个真实角色需要一个代理角色，多个真实角色会让代码量翻倍(仅静态代理中会出现，动态代理得以解决)

接口:
```java
// 租房
public interface Rent {
    // 出租
    public void rent();
}
```
真实角色:
```java
// 房东
public class Landlord implements Rent{
    @Override
    public void rent() {
        // 出租房产
        System.out.println("Rent out properties");
    }
}
```
代理角色:
```java
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
```
客户访问:
```java
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
```

### 动态代理

动态代理和静态代理的角色一样。

动态代理的代理类是动态生成的。

动态代理分两类: 基于接口的动态代理、基于类的动态代理

+ 基于接口: JDK动态代理(demo03, demo04)
+ 基于类: CGLIB
+ Java字节码实现: Javassist

需要了解两个类 proxy 代理、 InvocationHandler 调用处理程序

动态代理好处:
+ 一个动态代理类代理的是一个接口，一般对应了一类业务
+ 一个动态代理类可以代理多个类，只要是实现的同一个接口
+ 具有静态代理所有优点

## AOP(Aspect Oriented Programming)

面向切面编程。如上动态代理和静态代理的流程，可以理解拓展原先业务的一种方式。这种方法是Spring的重要内容。

AOP核心是代理模式，IoC核心是工厂模式。

### Spring中的AOP

提供声明式事务，允许用户自定义切面。

#### 方式一：使用Spring原生API

```java
public class Log implements MethodBeforeAdvice {
    /**
     * Callback before a given method is invoked.
     * @param method the method being invoked
     * @param args the arguments to the method
     * @param target the target of the method invocation. May be {@code null}.
     * @throws Throwable if this object wishes to abort the call.
     * Any exception thrown will be returned to the caller if it's
     * allowed by the method signature. Otherwise the exception
     * will be wrapped as a runtime exception.
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Class: " +
                target.getClass().getName() +
                "." +
                method.getName() +
                " in " +
                new Date());
    }
}
```

编写类继承MethodBeforeAdvice接口，实现before方法。

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="userService" class="com.iris.service.UserServiceImpl"/>
    <bean id="afterLog" class="com.iris.log.AfterLog"/>
    <bean id="log" class="com.iris.log.Log"/>

    <!-- 方式一：使用Spring原生api -->
    <!-- 切面需要配置 -->
    <aop:config>
        <!-- 需要切入点 expression 表达式-->
        <aop:pointcut id="pointcut" expression="execution(* com.iris.service.UserServiceImpl.*(..))"/>
        <!-- 执行环绕增加 -->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>

</beans>
```

xml中添加约束 `xmlns:aop="http://www.springframework.org/schema/aop"`

配置AOP `<aop:config>`

设置切入点，切入点上绑定环绕。

java中@param 的注释最好添加，由于spring代码不够完整，不添加注释可能会在xml中报错。

#### 方法二：使用自定义类实现

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="userService" class="com.iris.service.UserServiceImpl"/>
    <bean id="diyPointCut" class="com.iris.diypointcut.DiyPointCut"/>

    <!-- 方式二：自定义类 -->
    <!-- 切面需要配置 -->
    <aop:config>
        <!-- 自定义切入面 -->
        <aop:aspect ref="diyPointCut">
            <!-- 定义切入点 -->
            <aop:pointcut id="point" expression="execution(* com.iris.service.UserServiceImpl.*(..))"/>
            <!-- 通知：绑定方法 -->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>

</beans>
```

任何类都可以在xml中定义为切片，被放置到切入点前后执行。

#### 方式三：注解方式

```java
package com.iris.diypointcut;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect // 标记为切面
public class AnnotationPointCut {
    @Before("execution(* com.iris.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("before");
    }

    @After("execution(* com.iris.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("after");
    }

}
```

java类中添加注解

pom.xml中将作用域注释掉`<!-- <scope>runtime</scope> -->`

```xml
    <!-- 开启注解支持 -->
    <aop:aspectj-autoproxy/>
```

xml中开启注解支持。

