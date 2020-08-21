# Dubbo 
Dubbo缺省协议采用单一长连接和NIO异步通讯，适合于小数据量大并发的服务调用，以及服务消费者机器数远大于服务提供者机器数的情况。

作为RPC（网络协议调用）：支持各种传输协议，如dubbo,hession,http,rmi，底层采用netty长连接进行传输！典型的provider和cusomer模式!

作为SOA：具有服务治理功能，提供服务的注册和发现！用zookeeper实现注册中心！启动时候服务端会把所有接口注册到注册中心，  
并且订阅configurators,服务消费端订阅provide，  
configurators,routers,订阅变更时，zk会推送providers,configuators，routers,启动时注册长连接，进行通讯！  
proveider和provider启动后，后台启动定时器，发送统计数据到monitor（监控中心）！提供各种容错机制和负载均衡策略！！

## dubbo 的负载均衡 LoadBalance

AbstractLoadBalance 

                    -> Random  按照权重设置随机策略，原理：权重相加，递减invoker值，  
                                小于零的invoker执行

                    -> ConsistentHash 一致性哈希算法  相同参数的请求总是发到同一提供者（只对第一个参数进行hash，160个虚拟节点）。
                            可以通过nginx计算ip的hashcode，从而代理到一台服务器
                    
                    -> LeastActive  最小活跃数 内部维护一个active，调用+1，完成减1，活跃的最小最快
                    
                    -> RonundRobin  根据权重进轮训  从0开始递增，然后看落在哪个权重上，要根据权重总值取余
                     
                    -> 平滑加权算法  
                          静态权重：  A:B:C=5:1:1  动态权重 ： currentWeight：0:0:0
                          第一次请求  5:1:1           -2:1:1  return A
                          第二次请求  3:2:2           -4:2:2  return A   
                                     1:3:3           1：-4:3 return B
                                     6：-3:4         -1：-3:4 return A
                    AABAC
                            

## 服务暴露和消费的详细过程

![zookeeper](http://dubbo.apache.org/docs/zh-cn/user/sources/images/zookeeper.jpg)

（1）服务提供者暴露一个服务的详细过程

1. 提供API  
2. 提供实现类 
3. 注册服务（远程注册注册的是 服务名  +  主机的ip和端口），本地注册(服务名：实现类)）
4. 暴露服务（启动tomcat）


![图](https://img2020.cnblogs.com/i-beta/1703795/202003/1703795-20200320132941429-1596716664.png)
首先ServiceConfig类拿到对外提供服务的实际类ref(如：HelloWorldImpl),然后通过ProxyFactory类的getInvoker方法使用ref生成一个AbstractProxyInvoker实例，

到这一步就完成具体服务到Invoker的转化。接下来就是Invoker转换到Exporter的过程。

Dubbo处理服务暴露的关键就在Invoker转换到Exporter的过程(如上图中的红色部分)，下面我们以Dubbo和RMI这两种典型协议的实现来进行说明：

Dubbo的实现：

Dubbo协议的Invoker转为Exporter发生在DubboProtocol类的export方法，它主要是打开socket侦听服务，并接收客户端发来的各种请求，通讯细节由Dubbo自己实现。

RMI的实现：

RMI协议的Invoker转为Exporter发生在RmiProtocol类的export方法，
它通过Spring或Dubbo或JDK来实现RMI服务，通讯细节这一块由JDK底层来实现，这就省了不少工作量

（2）服务消费者消费一个服务的详细过程

1. 启东时从注册中心获取地址并缓存
2. 根据负载均衡选择一个服务地址调用

![图](https://img2020.cnblogs.com/i-beta/1703795/202003/1703795-20200320133111026-500313980.png)

首先ReferenceConfig类的init方法调用Protocol的refer方法生成Invoker实例(如上图中的红色部分)，这是服务消费的关键。

接下来把Invoker转换为客户端需要的接口(如：HelloWorld)。

传递参数 

+ 接口名
+ 方法名
+ 参数类型列表
+ 参数值列表

（3） 注册中心  

1. 保存服务名和服务地址映射  
2. 服务地址发生变动会通知消费者 通过Watcher监听


（4）监控中心 

统计服务的调用次数和调用时间



## RPC的解析过程 

解析服务： 
1）、基于dubbo.jar内的Meta-inf/spring.handlers配置，spring在遇到dubbo名称空间时，会回调DubboNamespaceHandler类。 
2）、所有的dubbo标签，都统一用DubboBeanDefinitionParser进行解析，基于一对一属性映射，将XML标签解析为Bean对象。 
在ServiceConfig.export 或者ReferenceConfig.get 初始化时，将Bean对象转会为url格式，将所以Bean属性转成url的参数。 
然后将URL传给Protocol扩展点，基于扩展点的Adaptive机制，根据URL的协议头，进行不同协议的服务暴露和引用。

## RPC通过jdk的动态代理的方式来实现调用远程服务的

首先消费方从代理类中获取服务提供的接口，当消费方调用服务的时候回执行invoke方法（根据负载均衡策略选择invoker），实现远程调用


## Dubbo支持的协议

    1.Dubbo协议(官方推荐协议) 采用hessian2序列化
     优点：
       采用NIO复用单一长连接，并使用线程池并发处理请求，减少握手和加大并发效率，性能较好（推荐使用）。
     缺点：
       大文件上传时,可能出现问题(不使用Dubbo文件上传)。
       
[Dubbo](http://dubbo.apache.org/zh-cn/docs/user/references/protocol/dubbo.html)       
   2、RMI(Remote Method Invocation)协议  采用java序列化
     优点:
       JDK自带的能力。可与原生RMI互操作，基于TCP协议。
     缺点:
       偶尔连接失败.。  
     在Spring中，通过代理模式，封装了Bean对象，客户端通过注入可以直接实现service方法
   3、Hessian协议  采用 json序列化
     优点:
       可与原生Hessian互操作，基于HTTP协议。
     缺点:
       需hessian.jar支持，http短连接的开销大。


## dubbo的序列化方案

1、RPC默认的序列化方式是使用的阿里修改或的hessian2,，而不是原生态的hessian2（非Java，夸语言的序列化方案） -----------效率2

2、Java原生态的序列化方式（性能差） -----------效率4

3、json序列化，非二进制流的序列化方式，性能没有二进制流的效率高 -----------效率3

4、dubbo序列化，阿里开发的并不成熟的高效的Java序列化，不建议在生产环境下使用 -----------效率1

## telnet localhost 22222

提供telnet监控命令

## 服务端配置

建议在 Provider 端配置的 Consumer 端属性有：

timeout：方法调用的超时时间  
retries：失败重试次数，缺省是 2 [2]   
loadbalance：负载均衡算法 [3]，缺省是随机 random。还可以配置轮询 roundrobin、最不活跃优先 [4] leastactive 和一致性哈希 consistenthash 等  
actives：消费者端的最大并发调用限制，即当 Consumer 对一个服务的并发调用到上限后，  
新调用会阻塞直到超时，在方法上配置 dubbo:method 则针对该方法进行并发限制，在接口上配置 dubbo:service，则针对该服务进行并发限制

owner：配置负责人

提供者列表缓存文件：  <dubbo:registry file=”${user.home}/output/dubbo.cache” />  


##  要使用Java SPI，需要遵循如下约定：

1、当服务提供者提供了接口的一种具体实现后，在jar包的META-INF/services目录下创建一个以“接口全限定名”为命名的文件，内容为实现类的全限定名；  
2、接口实现类所在的jar包放在主程序的classpath中；  
3、主程序通过java.util.ServiceLoder动态装载实现模块，它通过扫描META-INF/services目录下的配置文件找到实现类的全限定名，把类加载到JVM；  
4、SPI的实现类必须携带一个不带参数的构造方法；  

Dubbo在此基础上提供了IOC和AOP
[DUBBO的SPI扩展视频](https://www.bilibili.com/video/BV1zA411i7b3?p=73)
[DUBBO官网介绍](http://dubbo.apache.org/zh-cn/docs/dev/SPI.html)


AOP 类都命名为 XxxWrapper，基类都命名为 AbstractXxx。   
扩展点之间的组合将关系由 AOP 完成，ExtensionLoader 只负载加载扩展点，包括 AOP 扩展。   
尽量采用 IoC 注入扩展点之间的依赖，不要直接依赖 ExtensionLoader 的工厂方法。  
尽量采用 AOP 实现扩展点的通用行为，而不要用基类，比如负载均衡之前的 isAvailable 检查，它是独立于负载均衡之外的，不需要检查的是URL参数关闭  
对多种相似类型的抽象，用基类实现，比如 RMI, Hessian 等第三方协议都已生成了接口代理，只需将将接口代理转成 Invoker 即可完成桥接，它们可以用公共基类实现此逻辑。  
基类也是 SPI 的一部分，每个扩展点都应该有方便使用的基类支持。  

## Dubbo直连

1.修改服务提供者的配置文件：provider.xml

<dubbo:registry address="127.0.0.1:2181" register="false" />

2.在订阅配置文件consumer.xml中指定url
<dubbo:reference id="userFacade" interface="com.example.modules.user.UserFacade" url="dubbo://localhost:20880" />