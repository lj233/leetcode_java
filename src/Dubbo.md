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
                    
                    -> LeastActive  最小活跃数 内部维护一个active，调用+1，完成减1，活跃的最小最快
                    
                    -> RonundRobin  根据权重进轮训  轮循，按公约后的权重设置轮循比率 （维护了最大权重和最小权重、然后进行取模轮训）
                    

## 服务暴露和消费的详细过程

（1）服务提供者暴露一个服务的详细过程

1. 提供API  
2. 提供实现类 
3. 注册服务（远程注册，本地注册）（注册的是 服务名  +  主机的ip和端口）
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
2. 服务地址发生变动会通知消费者


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
   2、RMI(Remote Method Invocation)协议  采用java序列化
     优点:
       JDK自带的能力。可与原生RMI互操作，基于TCP协议。
     缺点:
       偶尔连接失败.。
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