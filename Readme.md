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

直接在属性上使用，也可以在set方法上使用。使用AutoWired后，可以省略set方法。注解其实是使用反射实现byName方法

@AutoWired(required = false) 使用required = false可使属性对象为空值且不报错。

@Qualifier(value = "dog11") 如果出现名称重复情况，可使用此标签重新byName定位。

set方法中的字段可以使用@Nullable注解，使方法值可以为null。不会报错。
public void setCat(@Nullable Cat cat)

#### @Resource

java自带了@Resource标签，作用与Autowired相同
@Resource(name = "dog")

### Field injection is not recommended
 
字段注入不被推荐。为什么不推荐？
 
1. NPE问题:

使用字段注入容易出现空指针问题，如下代码所示：因为Spring IOC容器在使用字段依赖注入时，并不会对依赖的bean是否为null做判断，因此在下面的代码中，通过 @Autowired 注入的user对象可能为空，而JVM 虚拟机在编译时也无法检测出user为null，只有在运行时调用user的方法时， 发现user为null，出现空指针异常(NPE)。

```java
    @Component
    public class FieldBasedInjection {
        private String name;
        
        @Autowired
        private final User user;
        public FieldBasedInjection(){
            this.name = user.getName(); // NPE
        }
    }
```
Java 在初始化一个类时，是按照 静态变量或静态语句块 ->实例变量或初始化语句块 -> 构造方法 -> @Autowired的顺序。所以在执行这个类的构造方法时，对象实际尚未被注入，它的值还是 null, 如果属性在被注入前就拿来使用就会导致npe(空指针错误)。

2. 和IOC容器耦合度太高

类通过属性输入，对外部不可见，类和容器的耦合度过高，导致无法脱离容器单独正确运行。比如下面的例子在Spring容器中运行没有问题。

```java
@RestController
public class TestHandleController {
    @Autowired
    TestHandleService testHandleService;
    public void helloTestService(){
        testHandleService.hello();
    }
}
```

如果我们用下面的方式调用呢？

```java
        TestHandleController testHandle = new TestHandleController();
        testHandle.helloTestService();  // 空指针
```

显而易见，就会出现空指针异常，依赖对外部不可见，外界可以看到构造器和setter，但无法看到私有字段，自然无法了解所需依赖，这样十分不利于单元测试。

3. 可能导致违反单一职责原则

使用基于字段的注解，非常简单好用无脑，我们无需关注类之间的依赖关系，完全依赖于Spring IOC容器的管理，但是使用”基于构造器注入的方式”， 我们需要手动在类代码中去编写需要依赖的类，当依赖的类越来越多，我们就能发现 code smell，这个时候就能显示的提醒我们，代码的质量是否有问题。因此，尽管字段注入不直接负责打破单一责任原则，但它通过隐藏了和构造器注入一样发现code smell的机会。示例代码：

```java
@Component 
public class ConstructorBasedInjection {
    private final Object object; 
    private final Object object2;         
    //         ... 
    private final Object objectX;
    
    @Autowired 
    public ConstructorBasedInjection(Object object, 
                                     Object object2, 
                                     //  ...       ,
                                     Object objectX) {
        this.object = object;
        this.object2 = object2; 
        //      ...     
        this.objectX = objectX; 
        }
}
```

4. 和Spring框架高度耦合

@Autowired是Spring框架中的注解，如果你的应用程序想要更换一个IOC框架，虽然这种情况非常非常低，这时候你就需要修改大量的代码了。更推荐的是使用 @Resource注解，@Resource注解是JSR-250提供的，它是Java标准，我们使用的IOC容器应当去兼容它，这样即使更换容器，也可以正常工作。

### *Recommended method

1. 【强烈推荐】使用构造器方式注入

    这也是Spring官方强烈推荐使用基于构造器注入的方式, 像国内Dubbo、RocketMQ等很多开源框架的源码都已经转向了基于构造器的注入方式，所以开发中我们应该尊重Spring官方的推荐，尽管其他的方式可以解决，但是不推荐。

2. 【一般推荐】使用@Resource注解

    如果你不喜欢构造器注入的方式，觉得使用构造器注入的方式麻烦，还要写代码，虽然不建议你这么想。那么更推荐你使用@Resource注解，@Resource是JSR-250提供的，不是Spring中的注解，它是Java标准，我们使用的IoC容器应当去兼容它，这样即使更换容器，也可以正常工作。如果你使用这个注解IDEA也不会提示警告。

#### @Autowired VS @Resource。
1. 提供方:
   - @Autowired是由Spring提供的，包名是: org.springframework.beans.factory.annotation
   - @Resource 是由Java提供的，包名是：javax.annotation
2. 依赖识别方式
   - @Autowired默认是以byType方式，可以使用@Qualifier指定bean名称，如果找不到Bean不会自动使用byName方式。
   - @Resource 默认是以byName方式，当byName方式无法匹配时，会使用byType方式。（仅适用于仅注册了一个Bean对象的类型）
3. 适用对象
   - @Autowired 可以使用在方法，方法参数，构造器，构造器参数，字段上
   -@Resource只能使用在方法，字段上(经过实测，无法注解在构造器和参数上)
4. 强依赖型
   - @Autowired和@Resource都是具有强依赖性，也就是必须要有这个bean才能启动，不过@Autowired可以设置属性required=false变成非强制注入。

## 注解完成所有功能

### @Component

放在类上，使该类被spring直接注册成bean，name为小写。

### @Value

放在属性上或者set方法上，直接赋值 @Value("Ode1l")

### @Component衍生注解

dao --> @Repository

service --> @Service

controller --> @Controller

po --> @Component

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

类用@Configuration标记为SpringConfig。类中通过例子中`@Bean`标记的方法该方法触发，效果等同于在XML中注册。

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

需要导入包aspectjweaver

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

## 结合 Mybatis

### 方式一

需要导入mybatis-spring, mybatis, spring-jdbc

1. mybatis-config.xml中数据库配置部分由spring-jdbc接管

```xml
<!-- DataSource: 使用Spring数据源替换Mybatis配置 c3p0 dbcp druid -->
<!-- 这里使用Spring提供的JDBC -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis
                ?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf-8&amp;
                allowMultiQueries=true&amp;useAffectedRows=true&amp;
                serverTimezone=Asia/Shanghai"/>
        <property name="username" value="root"/>
        <property name="password" value="112358"/>
    </bean>
```

2. SqlSessionFactory和mybatis-config中mapper部分，以及其余配置部分由SqlSessionFactoryBean接管

```xml
<!-- SqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!-- 绑定Mybatis配置文件,可以不用 -->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <!-- 可用*占位符一次引用一个包 -->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>
```

代替代码:
```java
    String resources = "mybatis-config.xml";
    InputStream in = Resources.getResourceAsStream(resources);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
```

3. SqlSession 由 SqlSessionTemplate 来创建，创建时参数只能使用构造器方式。
```xml
<!-- SqlSessionTemplate是SqlSession,只能使用构造器注入！！！因为没有set方法-->
    <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
```

代替代码:
```java
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
```

4. 给接口写实现类（实体类），主要提供是提供set方法用来autowired。

```java
package com.iris.mapper;

import com.iris.po.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper {
    private SqlSessionTemplate sqlSession;
    
    // set方法提供注入接口
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> query() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.query();
    }
}
```

5. 注入实现类并调用
```xml
    <bean id="userMapperImpl" class="com.iris.mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSessionTemplate"/>
    </bean>
```
```java
        UserMapperImpl userMapper = context.getBean(UserMapperImpl.class);
        List<User> userList = userMapper.query();
        userMapper.query().forEach(System.out::println);
```

### 方式二

```java
package com.iris.mapper;

import com.iris.po.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserMapper  {
    @Override
    public List<User> query() {
        return getSqlSession().getMapper(UserMapper.class).query();
    }
}
```

只需在实现类中继承`SqlSessionDaoSupport`类，方法中使用`getSqlSession().getMapper(UserMapper.class)`反射来获取。

```xml
    <bean id="userDaoImpl" class="com.iris.mapper.UserDaoImpl">
        <!-- 可以注入factory，也可也注入template。官方建议factory -->
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
        <!-- <property name="sqlSessionTemplate" ref="sqlSessionTemplate"/> -->
    </bean>
```

springbean中需要绑定factory或者template都可以。

## 事务(transaction)管理

1. 声明式事务
2. 编程式事务

#### 编程式

在需要事务管理的代码段使用`try` `catch` 管理

```java
@Override
    public void invoke() {
        TransactionStatus txStatus =
                transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            User user = new User(3,"橙猫猫","156");
            userMapper.add(user);
            userMapper.delete(3);
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            throw e;
        }
        transactionManager.commit(txStatus);
    }
```

### 声明式(推荐)

```xml
    <!-- 配置声明式事务 DataSourceTransactionManager  -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!-- 可以使用属性方式，官方使用构造器方式 -->
        <!-- <property name="dataSource" ref="dataSource"/> -->
        <!-- 构造器方式同理 -->
        <constructor-arg ref="dataSource" />
    </bean>

    <!-- 结合AOP，实现事务植入 -->
    <!-- 配置事务通知 -->
    <tx:advice id="transactionInterceptor" transaction-manager="transactionManager">
        <!-- 给哪些方法配置事务 -->
        <!-- 配置事务的传播特性 propagation 默认 REQUIRED -->
        <tx:attributes>
            <!-- <tx:method name="add" propagation="REQUIRED"/> -->
            <!-- <tx:method name="update" propagation="REQUIRED"/> -->
            <!-- <tx:method name="delete" propagation="REQUIRED"/> -->
            <!-- <tx:method name="query" read-only="true"/> -->
            <tx:method name="*"/>
        </tx:attributes>
    </tx:advice>

    <!-- 配置事务切入点 -->
    <aop:config>
        <aop:pointcut id="txPointCut" expression="execution(* com.iris.service.*.*(..))"/>
        <aop:advisor advice-ref="transactionInterceptor" pointcut-ref="txPointCut"/>
    </aop:config>
```

在配置文件中添加transactionManager。给方法绑定事务。配置切入点。

案例中，我在UserServiceImpl中写的invoke方法内，先增加一个用户，再删除一个用户，删除的sql做了手脚无法执行。因此会执行报错。开启事务后，执行错误了依然会回滚，数据库中没有测试的用户。

mybatis-spring官方文档使用的案例是将transactionManager绑定到了factory上，这种案例没有实际意义，最有实际意义的是这种声明式的绑定方式，会使原先代码没有任何改动。