# 控制反转思想IoC

在之前的业务中，用户的需求可能会影响业务代码，我们需要根据用户的需求修改源代码，如果程序代码量十分庞大，修改一次的成本代价十分昂贵。

我么使用一个Set接口实现，已经发生了革命性的变化。
```java 
    private UserDao userDao;
    // 利用set控制动态实现值的注入
    @Override
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
```

之前，，程序是主动创建对象，控制权在程序员手上。
使用set注入之后，程序不再具有主动性，而是变成被动的接收对象。

这种思想从本质上解决了问题，程序员不再管理对象的创建new，系统耦合性大大降低。可以更加专注在业务的实现上。
所谓的控制反转，是只获得依赖对象的方式反转了。
这是IOC的原型prototype，但不是完整的IOC。

控制反转，是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的放松。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection，DI）。
DI 也是IoC的一种是实现方法。

IoC容器

