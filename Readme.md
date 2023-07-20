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
