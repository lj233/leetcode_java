#spring 原理

它是一个全面的、企业应用开发一站式的解决方案，贯穿表现层、业务层、持久层。
但是 Spring 仍然可以和其他的框架无缝整合。 
## Spring 特性 
 
+ 轻量级 
+ 控制反转 
+ 面向切面 
+ 容器 
+ 框架集合 

## Spring Bean 工厂

Ioc控制反转的思想 和 Ioc容器（Map（key，value））  
IOC 初始化 ： 根据XML读取resource-》BeanDefinition解析-》注册BeanFactory

1 、 在BeanDifintion中设置属性 beanclass、parentName、Scope、lazyinit等
2. Bean工厂后置处理器 BeanFactoryPostProcessor
3、 创建Bean容器 实例化、循环依赖、AOP、依赖注入 

## DI 依赖注入 

依赖注入会将所依赖的关系自动交给目标对象，而不是让对象自己去获取依赖。  

eg；构造器注入，成员变量将被指定为构造器传入的参数类型，而不是自己new。

## 获取bean的方式 

1. @Bean注解
2. FactoryBean-》getObject返回的类型
3. 利用register手动注册 


BeanFactory - 大工厂 -包含所有的SpringBean

FactoryBean - 小工厂 -只能返回单种类型

获取单个Mapper 
```
    //FactoryBean中重写的方法
    public Object getObject(){
        //jdk动态代理
        UserMapper userMapper = （UserMapper）Proxy.newProxyInstance(FactoryBean.class.getClassLoader(), new Class[]{UserMapper.class}, new InvocationHandler() {
            //proxy 为代理对象 ， method为userMapper的方法
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (Object.class.equals(method.getDeclaringClass())){
                    return method.invoke(args);
                }
                return null;
            }
        });
        return userMapper;
    }
```
获取多个Mapper
在FactoryBean中增加  
private class Mapper；  
利用@Import注解把注册的Bean导入



## Spring 循环依赖 

![循环依赖和解决方案](https://www.jianshu.com/p/b65c57f4d45d)

## Spring 模块

各种模块集成了项目需要用到的基本框架，提供了其他第三方框架的集成点。

Core

AOP

ORM

DAO

Web

Spring EE
## Spring Ioc原理
概念： 通过一个配置文件描述 Bean 及 Bean 之间的依赖关系，利用 Java 语言的反射功能实例化 Bean 并建立 Bean 之间的依赖关系。   
Spring 的 IoC 容器在完成这些底层工作的基础上，还提供 了 Bean 实例缓存、生命周期管理、 Bean 实例代理、事件发布、资源装载等高级服务


如何注入bean：
1. 扫描XML中的 <bean>、java类中的@Configuration和@Autowired
2. 在Spring容器中生成Bean定义注册表
3. 根据注册表实例化bean
4. 将Bean放到spring容器中（bean缓存池）
5. 使用Bean


## IOC 容器实现

BeanFactory  框架基础设施

    BeanFactory 是 Spring 框架的基础设施，面向 Spring 本身；ApplicationContext 面向使用 Spring 框架的开发者，几乎所有的应用场合我们都直接使用 ApplicationContext 而非底层 的 BeanFactory。 
 
ApplicationContext 面向开发应用

    ApplicationContext 由 BeanFactory 派生而来，提供了更多面向实际应用的功能。
    ApplicationContext 继承了 HierarchicalBeanFactory 和 ListableBeanFactory 接口，在此基础 上，还通过多个其他的接口扩展了 BeanFactory 的功能

WebApplication 体系架构

     WebApplicationContext 是专门为 Web 应用准备的，它允许从相对于 Web 根目录的 路径中装载配置文件完成初始化工作。从 WebApplicationContext 中可以获得
     ServletContext 的引用，整个 Web 应用上下文对象将作为属性放置到 ServletContext 中，以便 Web 应用环境可以访问 Spring 应用上下文
     
     
## spring bean 的作用域

    分别为 singleton（单例）、 默认的，无论注入其他Bean多少次，都是同一个bean
    prototype 原型模式每次使用时创建 注入或者通过上下文获取的时候，都会创建新的bean
    request、一次http请求一个bean
    session 不同的实例之间不共享属性，且实例仅在自己的 session 请求
            内有效，请求结束，则实例将被销毁
            需要配置代理  
    global session 全局的
    
## Spring中的单例bean的线程安全问题了解吗？

大部分时候我们并没有在系统中使用多线程，所以很少有人会关注这个问题。单例bean存在线程问题，  
主要是因为当多个线程操作同一个对象的时候，对这个对象的非静态成员变量的写操作会存在线程安全问题。

有两种常见的解决方案：

1.在bean对象中尽量避免定义可变的成员变量（不太现实）。

2.在类中定义一个ThreadLocal成员变量，将需要的可变成员变量保存在ThreadLocal中（推荐的一种方式）。   
    
## spring bean 的生命周期

   + 实例化
   + 依赖注入 、setName等
   + 执行自定义接口方法  如果实现了其他*Aware接口，就调用相应的方法。
   + 如果有和加载这个Bean的Spring容器相关的BeanPostProcessor对象，执行postProcessBeforeInitialization()方法。 
   + 如果Bean实现了InitializingBean接口，执行afeterPropertiesSet()方法。
   + 调用destory方法
   
## Bean装配的方式
+ 显式基于XML
+  显式基于JAVA
+ 隐式自动装配
    + 组件扫描 component scanning 
    + 自动装配 autowiring
 
   
## spring 依赖注入4种方式

 + 构造器注入
  ```
/*带参数，方便利用构造器进行注入*/
 public CatDaoImpl(String message){
 this. message = message;
 }
<bean id="CatDaoImpl" class="com.CatDaoImpl">
<constructor-arg value=" message "></constructor-arg>
</bean>
 ```
 
 + setter方法注入
 ```
 public class Id {
 private int id;
 public int getId() { return id; }
 public void setId(int id) { this.id = id; }
}
<bean id="id" class="com.id "> <property name="id" value="123"></property> </bean> 
```
  + 静态工厂注入
  ```
public class DaoFactory { //静态工厂
 public static final FactoryDao getStaticFactoryDaoImpl(){
 return new StaticFacotryDaoImpl();
 }
}
public class SpringAction {
 private FactoryDao staticFactoryDao; //注入对象
 //注入对象的 set 方法
 public void setStaticFactoryDao(FactoryDao staticFactoryDao) {
 this.staticFactoryDao = staticFactoryDao;
 }
}
//factory-method="getStaticFactoryDaoImpl"指定调用哪个工厂方法
 <bean name="springAction" class=" SpringAction" >
 <!--使用静态工厂的方法注入对象,对应下面的配置文件-->
 <property name="staticFactoryDao" ref="staticFactoryDao"></property>
 </bean>
 <!--此处获取对象的方式是从工厂类中获取静态方法-->
<bean name="staticFactoryDao" class="DaoFactory"
factory-method="getStaticFactoryDaoImpl">
</bean>
```
  + 实例工厂
    需要先在xml中new对象
 ```
public class DaoFactory { //实例工厂
 public FactoryDao getFactoryDaoImpl(){
 return new FactoryDaoImpl(); 
13/04/2018 Page 128 of 283
 }
}
public class SpringAction {
 private FactoryDao factoryDao; //注入对象
 public void setFactoryDao(FactoryDao factoryDao) {
 this.factoryDao = factoryDao;
 }
}
 <bean name="springAction" class="SpringAction">
 <!--使用实例工厂的方法注入对象,对应下面的配置文件-->
 <property name="factoryDao" ref="factoryDao"></property>
 </bean>
 <!--此处获取对象的方式是从工厂类中获取实例方法-->
<bean name="daoFactory" class="com.DaoFactory"></bean>
<bean name="factoryDao" factory-bean="daoFactory"
factory-method="getFactoryDaoImpl"></bean> 
```

## aop

AOP 核心概念
1、切面（aspect）：类是对物体特征的抽象，切面就是对横切关注点的抽象

2、横切关注点：对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点。

3、连接点（joinpoint）：被拦截到的点，因为 Spring 只支持方法类型的连接点，所以在 Spring
中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器。

4、切入点（pointcut）：对连接点进行拦截的定义

5、通知（advice）：所谓通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、
异常、最终、环绕通知五类。

6、目标对象：代理的目标对象

7、织入（weave）：将切面应用到目标对象并导致代理对象创建的过程


8、引入（introduction）：在不修改代码的前提下，引入可以在运行期为类动态地添加一些方法
或字段

AOP 两种代理方式

Spring 提供了两种方式来生成代理对象: JDKProxy 和 Cglib，具体使用哪种方式生成由  
AopProxyFactory 根据 AdvisedSupport 对象的配置来决定。  
默认的策略是如果目标类是接口，  则使用 JDK 动态代理技术，  
否则使用 Cglib 来生成代理，回生成一个被代理对象的子类

    Spring事务基于Spring AOP，Spring AOP底层用的动态代理，动态代理有两种方式：
    
    基于接口代理(JDK代理)
    
    基于接口代理，凡是类的方法非public修饰，或者用了static关键字修饰，那这些方法都不能被Spring AOP增强
    
    基于CGLib代理(子类代理)
    
    基于子类代理，凡是类的方法使用了private、static、final修饰，那这些方法都不能被Spring AOP增强

实现方式

```java
@Aspect
public class TransactionDemo {
 @Pointcut(value="execution(* com.yangxin.core.service.*.*.*(..))")
 public void point(){
 }
 @Before(value="point()")
 public void before(){
 System.out.println("transaction begin");
 }
 @AfterReturning(value = "point()")
 public void after(){
 System.out.println("transaction commit");
 }
 @Around("point()")
 public void around(ProceedingJoinPoint joinPoint) throws Throwable{
 System.out.println("transaction begin");
 joinPoint.proceed();
 System.out.println("transaction commit");
 }
}
```



## @Component和@Bean的区别是什么

1.作用对象不同。@Component注解作用于类，而@Bean注解作用于方法。

2.@Component注解通常是通过类路径扫描来自动侦测以及自动装配到Spring容器中  
（我们可以使用@ComponentScan注解定义要扫描的路径）。@Bean注解通常是在标有该注解的方法中定义产生这个bean，告诉Spring这是某个类的实例，当我需要用它的时候还给我。

3.@Bean注解比@Component注解的自定义性更强，而且很多地方只能通过@Bean注解来注册bean。  
比如当引用第三方库的类需要装配到Spring容器的时候，就只能通过@Bean注解来实现。



## Spring事务中的隔离级别有哪几种？

在TransactionDefinition接口中定义了五个表示隔离级别的常量：

ISOLATION_DEFAULT：使用后端数据库默认的隔离级别，Mysql默认采用的REPEATABLE_READ隔离级别；Oracle默认采用的READ_COMMITTED隔离级别。

ISOLATION_READ_UNCOMMITTED：最低的隔离级别，允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读。

ISOLATION_READ_COMMITTED：允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生

ISOLATION_REPEATABLE_READ：对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生。

ISOLATION_SERIALIZABLE：最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，  
也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。


## Spring事务中有哪几种事务传播行为？

这种情况是由数据库同一个连接完成

如果是由不同的系统完成，即使用分布式事务，阿里seata

在TransactionDefinition接口中定义了七个表示事务传播行为的常量。

支持当前事务的情况：

PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。  
> 外围没开启，两个事务会各自执行自己的事务，，
>>外围方法抛异常不会影响内部的事务
>> 回滚也只是影响自己的事务
>外围开启，则只要方法体内抛出异常，都要回滚，即使事务异常被catch捕捉

PROPAGATION_SUPPORTS： 如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。

PROPAGATION_MANDATORY： 如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。（mandatory：强制性）。

不支持当前事务的情况：

PROPAGATION_REQUIRES_NEW： 创建一个新的事务，如果当前存在事务，则把当前事务挂起。
> 外围没开启事务，内部事务独立运行，不会受方法体内异常影响
>> 内部事务抛出异常，自己回滚
>开启事务 
>> 内部方法如果是同一个级别，会受到回滚影响
>> 内部方法会自己回滚，如果抛出异常会影响外围方法
>> 内部事务方法抛出异常如果被catch捕捉，则不会回滚

PROPAGATION_NOT_SUPPORTED： 以非事务方式运行，如果当前存在事务，则把当前事务挂起。

PROPAGATION_NEVER： 以非事务方式运行，如果当前存在事务，则抛出异常。

其他情况：

PROPAGATION_NESTED： 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于PROPAGATION_REQUIRED。
> 外围事务回滚，子事务也要回滚
> 子事务如果被捕捉，可以单独回滚

[事务传播](https://segmentfault.com/a/1190000013341344)

## spring mvc
Spring MVC下我们一般把后端项目分为Service层（处理业务）、Dao层（数据库操作）、Entity层（实体类）、Controller层（控制层，返回数据给前台页面）。  

mvc流程

![mvc流程](img/spring_mvc.png)


## SPRING BOOT 原理

1. 创建独立的 Spring 应用程序
2. 嵌入的 Tomcat，无需部署 WAR 文件
3. 简化 Maven 配置
4. 自动配置 Spring
5. 供生产就绪型功能，如指标，健康检查和外部配置
6. 绝对没有代码生成和对 XML 没有要求配置 


 
# 微服务

# 服务注册与发现
    + 客户端注册（zookerper）
        自身要负责注册与注销的工作
    + 第三方注册 
        由一个独立的服务Registrar负责注册与注销
        
    + 客户端发现 
        客户端负责查询可用服务地址（缺点也在于多语言时的重复工作）
        
    + 服务端发现
        服务端发现需要额外的 Router 服务，请求先打到 Router，然后 Router 负责查询服务与负载均衡。

## Netty与RPC

    Netty 是一个高性能、异步事件驱动的 NIO 框架，基于 JAVA NIO 提供的 API 实现。它提供了对
    TCP、UDP 和文件传输的支持，作为一个异步 NIO 框架，Netty 的所有 IO 操作都是异步非阻塞
    的，通过 Future-Listener 机制，用户可以方便的主动获取或者通过通知机制获得 IO 操作结果。

# 拦截器 监听器 过滤器

+ Spring *MVC* 中的拦截器（Interceptor）类似于Servlet中的过滤器（Filter）通过实现HandlerInterceptor/WebRequestInterceptor接口

    > 重写其中的handle等方法
    
+ 监听器 就是application、session、request三个对象创建、销毁或者往其中添加修改删除属性时自动执行代码的功能组件


```
①ServletContextListener：对Servlet上下文的创建和销毁进行监听
③HttpSessionListener：对Session的创建和销毁进行监听。
⑤ServletRequestListener：对请求对象的初始化和销毁进行监听
```

+ 过滤器 实现Filter 的dochain（）方法，可以多次执行，责任链模式

## bean 的生命周期和注入方式

[bean的生命周期](https://mmbiz.qpic.cn/sz_mmbiz_png/2BGWl1qPxib1Id9lfLbDPG8Qbc5RVwMpMiabGNIxCoHWt6CAHSmGxXDlDfznAJ7T3xHK3dgXdzMGWSgfCeYRIYicw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

## Spring Boot自动装配

@SpringBootApplication ->@SpringBootConfiguration 
                         @EnableAutoConfiguration ->@Import({AutoConfigurationImportSelector.class}) -> META-INF->spring.factories
                         @ComponentScan
                         

## @Autowired和@Resource

@Autowired 默认按照Bytype注入
@Resource  默认按照Byname注入

@Primary 设置为首选的bean
@Qualifier 指定唯一的bean
## Spring 容器

1. BeanFactory  Bean工厂  （太低级）
2. ApplicationContext 应用上下文  getBean（）方法获取
    ClassPathXmlApplicationContext  
    FileSystemXmlApplicationContext等  
    
## 远程调用服务 

+ RMI 在Spring中，通过代理模式，封装了Bean对象，客户端通过注入可以直接实现service方法，无法穿透防火墙
+ HttpInvoker 基于Http的远程调用，导出了基于HttpInvokerServiceExport的bean对象