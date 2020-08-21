# 网络
## 网络 7 层架构

        7 层模型主要包括：
        1. 物理层：主要定义物理设备标准，如网线的接口类型、光纤的接口类型、各种传输介质的传输速率
        等。它的主要作用是传输比特流（就是由 1、0 转化为电流强弱来进行传输,到达目的地后在转化为
        也就是我们常说的模数转换与数模转换）。这一层的数据叫做比特。
        2. 数据链路层：主要将从物理层接收的数据进行 MAC 地址（网卡的地址）的封装与解封装。常把这
        一层的数据叫做帧。在这一层工作的设备是交换机，数据通过交换机来传输。
        3. 网络层：主要将从下层接收到的数据进行 IP 地址（例 192.168.0.1)的封装与解封装。在这一层工
        作的设备是路由器，常把这一层的数据叫做数据包。
        4. 传输层：定义了一些传输数据的协议和端口号（WWW 端口 80 等），如：TCP（传输控制协议，
        传输效率低，可靠性强，用于传输可靠性要求高，数据量大的数据），UDP（用户数据报协议，
        与 TCP 特性恰恰相反，用于传输可靠性要求不高，数据量小的数据，如 QQ 聊天数据就是通过这
        种方式传输的）。 主要是将从下层接收的数据进行分段进行传输，到达目的地址后在进行重组。
        常常把这一层数据叫做段。
        5. 会话层：通过传输层（端口号：传输端口与接收端口）建立数据传输的通路。主要在你的系统之间
        发起会话或或者接受会话请求（设备之间需要互相认识可以是 IP 也可以是 MAC 或者是主机名）
        6. 表示层：主要是进行对接收的数据进行解释、加密与解密、压缩与解压缩等（也就是把计算机能够
        识别的东西转换成人能够能识别的东西（如图片、声音等））
        7. 应用层 主要是一些终端的应用，比如说FTP（各种文件下载），WEB（IE浏览），QQ之类的（你
        就把它理解成我们在电脑屏幕上可以看到的东西．就 是终端应用）。


TCP/IP 由四个层次组成：网络接口层、网络层、传输层、应用层。

创建TCP连接，发送http/ 2.0请求

```
Seq序号，占32位，用来标识从TCP源端向目的端发送的字节流，发起方发送数据时对此进行标记
URG：紧急指针（urgent pointer）有效。
ACK：确认序号有效。
PSH：接收方应该尽快将这个报文交给应用层。
RST：重置连接。
SYN：发起一个新连接。
FIN：释放一个连接
```

### 三次握手: 减少服务器开销和接收到失效请求发生的错误  

    第一次握手：主机 A 发送位码为 syn＝1,随机产生 seq number=1234567 的数据包到服务器，主机 B
    由 SYN=1 知道，A 要求建立联机； 
    客户端进入SYN-SENT阶段。
    第二次握手：主机 B 收到请求后要确认联机信息，向 A 发 送 ack number=( 主 机 A 的
    seq+1),syn=1,ACK=1,随机产生 seq=7654321 的包
    服务器端进入SYN-RCVD阶段。
    第三次握手：主机 A 收到后检查 ack number 是否正确，即第一次发送的 seq number+1,以及位码
    ack 是否为 1，若正确，主机 A 会再发送 ack number=(主机 B 的 seq+1),ack=1，主机 B 收到后确认
    seq 值与 ack=1 则连接建立成功。
    客户端进入ESTABLISHED阶段。


### 四次挥手

    TCP 建立连接要进行三次握手，而断开连接要进行四次。这是由于 TCP 的半关闭造成的。因为 TCP 连
    接是全双工的(即数据可在两个方向上同时传递)所以进行关闭时每个方向上都要单独进行关闭。这个单
    方向的关闭就叫半关闭。当一方完成它的数据发送任务，就发送一个 FIN 来向另一方通告将要终止这个
    方向的连接。
    1） 关闭客户端到服务器的连接：首先客户端 A 发送一个 FIN，用来关闭客户到服务器的数据传送，
    然后等待服务器的确认。其中终止标志位 FIN=1，序列号 seq=u
    2） 服务器收到这个 FIN，它发回一个 ACK，确认号 ack 为收到的序号加 1。
     3） 关闭服务器到客户端的连接：也是发送一个 FIN 给客户端。
    4） 客户段收到 FIN 后，并发回一个 ACK 报文确认，并将确认序号 seq 设置为收到序号加 1。
     首先进行关闭的一方将执行主动关闭，而另一方执行被动关闭。
     
 [图片详解](https://pics5.baidu.com/feed/48540923dd54564e5260495ce0006487d0584fb6.jpeg?token=c3a743af38e25ff66deb6a07891be58e&s=C584FC1A71CFF4EE1A75A45203007073)
 
 
http状态

    消息响应
    100 Continue(继续)
    101 Switching Protocol(切换协议)
    成功响应
    200 OK(成功)
    201 Created(已创建)
    202 Accepted(已创建)
    203 Non-Authoritative Information(未授权信息)
    204 No Content(无内容)
    205 Reset Content(重置内容)
    206 Partial Content(部分内容)
    重定向
    300 Multiple Choice(多种选择)
    301 Moved Permanently(永久移动)
    302 Found(临时移动)
    303 See Other(查看其他位置)
    304 Not Modified(未修改)
    305 Use Proxy(使用代理)
    306 unused(未使用)
    307 Temporary Redirect(临时重定向)
    308 Permanent Redirect(永久重定向)
    客户端错误
    400 Bad Request(错误请求)
    401 Unauthorized(未授权)
    402 Payment Required(需要付款)
    403 Forbidden(禁止访问)
    404 Not Found(未找到)
    405 Method Not Allowed(不允许使用该方法)
    406 Not Acceptable(无法接受)
    407 Proxy Authentication Required(要求代理身份验证)
    408 Request Timeout(请求超时)
    409 Conflict(冲突)
    410 Gone(已失效)
    411 Length Required(需要内容长度头)
    412 Precondition Failed(预处理失败)
    413 Request Entity Too Large(请求实体过长)
    414 Request-URI Too Long(请求网址过长)
    415 Unsupported Media Type(媒体类型不支持)
    416 Requested Range Not Satisfiable(请求范围不合要求)
    417 Expectation Failed(预期结果失败)
    服务器端错误
    500 Internal Server Error(内部服务器错误)
    501 Implemented(未实现)
    502 Bad Gateway(网关错误)
    503 Service Unavailable(服务不可用)
    504 Gateway Timeout (网关超时)
    505 HTTP Version Not Supported(HTTP 版本不受支持)
    
## HTTP响应
一个HTTP响应代表着服务器向浏览器回送数据

一个完整的HTTP响应应该包含四个部分:

一个状态行【用于描述服务器对请求的处理结果。】

多个消息头【用于描述服务器的基本信息，以及数据的描述，服务器通过这些数据的描述信息，可以通知客户端如何处理等一会儿它回送的数据】

一个空行

实体内容【服务器向客户端回送的数据】
 
## Kafka 概念

    Kafka 是一种高吞吐量、分布式、基于发布/订阅的消息系统，最初由 LinkedIn 公司开发，使用
    Scala 语言编写，目前是 Apache 的开源项目。
    1. broker：Kafka 服务器，负责消息存储和转发
    2. topic：消息类别，Kafka 按照 topic 来分类消息
    3. partition：topic 的分区，一个 topic 可以包含多个 partition，topic 消息保存在各个
    partition 上
    4. offset：消息在日志中的位置，可以理解是消息在 partition 上的偏移量，也是代表该消息的
    唯一序号
    5. Producer：消息生产者
    6. Consumer：消息消费者
    7. Consumer Group：消费者分组，每个 Consumer 必须属于一个 group
    8. Zookeeper：保存着集群 broker、topic、partition 等 meta 数据；另外，还负责 broker 故
    障发现，partition leader 选举，负载均衡等功能

## RabbitMQ 概念

    RabbitMQ 是一个由 Erlang 语言开发的 AMQP 的开源实现。
    AMQP ：Advanced Message Queue，高级消息队列协议。它是应用层协议的一个开放标准，为
    面向消息的中间件设计，基于此协议的客户端与消息中间件可传递消息，并不受产品、开发语言
    等条件的限制。
    RabbitMQ 最初起源于金融系统，用于在分布式系统中存储转发消息，在易用性、扩展性、高可
    用性等方面表现不俗。具体特点包括：
    1. 可靠性（Reliability）：RabbitMQ 使用一些机制来保证可靠性，如持久化、传输确认、发布
    确认。
    2. 灵活的路由（Flexible Routing）：在消息进入队列之前，通过 Exchange 来路由消息的。对
    于典型的路由功能，RabbitMQ 已经提供了一些内置的 Exchange 来实现。针对更复杂的路
    由功能，可以将多个 Exchange 绑定在一起，也通过插件机制实现自己的 Exchange 。
    3. 消息集群（Clustering）：多个 RabbitMQ 服务器可以组成一个集群，形成一个逻辑 Broker 。
    4. 高可用（Highly Available Queues）：队列可以在集群中的机器上进行镜像，使得在部分节
    点出问题的情况下队列仍然可用。
    5. 多种协议（Multi-protocol）：RabbitMQ 支持多种消息队列协议，比如 STOMP、MQTT
    等等。
    6. 多语言客户端（Many Clients）：RabbitMQ 几乎支持所有常用语言，比如 Java、.NET、
    Ruby 等等。
    7. 管理界面（Management UI）:RabbitMQ 提供了一个易用的用户界面，使得用户可以监控
    和管理消息 Broker 的许多方面。
    8. 跟踪机制（Tracing）:如果消息异常，RabbitMQ 提供了消息跟踪机制，使用者可以找出发生
    了什么。
    9. 插件机制（Plugin System）:RabbitMQ 提供了许多插件，来从多方面进行扩展，也可以编
    写自己的插件。
    
    
## Hbase 概念 NoSql

    Hbase 是分布式、面向列的开源数据库（其实准确的说是面向列族）。HDFS(分布式文件系统管理) 为 Hbase 提供可靠的
    底层数据存储服务，MapReduce 为 Hbase 提供高性能的计算能力，Zookeeper 为 Hbase 提供
    稳定服务和 Failover 机制，因此我们说 Hbase 是一个通过大量廉价的机器解决海量数据的高速存
    储和读取的分布式数据库解决方案
   
   在HBase里边，先有列族，后有列。
   
   什么是列族？可以简单理解为：列的属性类别
   
   什么是列修饰符？先有列族后有列，在列族下用列修饰符来标识一列
   
   读取最新的时间戳  
   
   [Hbase](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247488766&idx=1&sn=f18350b152d51e464b9c5a55ff9e4bbf&chksm=ebd755ffdca0dce9429440b10f239263a979227edea1fee4c62082316140c29e0659e11e9ff1&token=1936697047&lang=zh_CN#rd)
    
    
## MongoDB 概念

    MongoDB 是由 C++语言编写的，是一个基于分布式文件存储的开源数据库系统。在高负载的情
    况下，添加更多的节点，可以保证服务器性能。MongoDB 旨在为 WEB 应用提供可扩展的高性能
    数据存储解决方案。
    MongoDB 将数据存储为一个文档，数据结构由键值(key=>value)对组成。MongoDB 文档类似
    于 JSON 对象。字段值可以包含其他文档，数组及文档数组。
    
    
## HDFS  分布式文件系统

如果是写操作，HDFS切分完文件以后，会询问NameNode应该将这些切分好的block往哪几台DataNode上写。

如果是读操作，HDFS拿到文件名，也会去询问NameNode应该往哪几台DataNode(存放真实数据的服务器)上读数据。

> 不同的服务器之间进行数据备份


## storm 分布式的实时计算系统  

实时：处理速度是毫秒级或者秒级的

计算：可以简单理解为对数据进行处理，比如清洗数据（对数据进行规整，取出有用的数据）

## [ 中间件的优缺点 ](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247487284&idx=1&sn=59a7683b2ca942f77384ddc35f662cf0&chksm=ebd74e35dca0c72350ddb05be6d06b2aaa29ff0e4f1d9aa3db3c5131decc71b18180e8c3cf17&token=1857725064&lang=zh_CN#rd)

# 幂等性

任意多次执行所产生的影响均与一次执行的影响相同

> 在编程中一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同

## 幂等性实现原理 

+ Redis实现 Token机制 先请求一个token，携带token请求，第一次请求则删除token，没有则为重复操作

+ 数据库去重表  存储一个唯一序列号，可以用自定义注解做

+ 状态位判断

## Webflux 响应式编程的一个子模块（WEB端）

异步非阻塞，Servlet 3.1就已经支持异步非阻塞了。

好处：只需要在程序内启动少量线程扩展，而不是水平通过集群扩展。  
异步能够规避文件IO/网络IO阻塞所带来的线程堆积

> 响应式编程（reactive programming）是一种基于数据流（data stream）和变化传递（propagation of change）的声明式（declarative）的编程范式
>
>

WebFlux什么场景下可以替换SpringMVC呢？

> 想要内存和线程数较少的场景

> 网络较慢或者IO会经常出现问题的场景

  有Lambda表达式和Stream流的基础，在学API.  
  一种是SpringMVC 注解的，一种是叫Functional Endpoints的
  
  
## 埋点 就是打日志 

在系统关键位置打印日志，方便排查问题

1. 收集日志
2. 清洗日志
3. 输出到数据源

收集日志我们有logAgent帮我们收集到Kafka，  
实时清洗日志我们用的就是Storm，  
清洗完我们输出到Redis(实时)/Hive（离线）。

[storm](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247487284&idx=1&sn=59a7683b2ca942f77384ddc35f662cf0&chksm=ebd74e35dca0c72350ddb05be6d06b2aaa29ff0e4f1d9aa3db3c5131decc71b18180e8c3cf17&token=1857725064&lang=zh_CN#rd)

## Docker 

+ 镜像 包含了各种运行环境，可以通过Dockerfile构建 (build)  docker image 查看，包含了很多层，可以继承
+ 容器 运行起来的镜像 （run） 
> docker ps 查看， 
>退出：exit 容器停止退出； 快捷键： ctrl+p+q 容器不停止退出。  
>启动 docker start dcentos   
>重启 docker restart dcentos  
>停止 docker stop dcentos  

## ES Elasticsearch  是一个实时的分布式存储、搜索、分析的引擎。

相对于数据库，Elasticsearch的强大之处就是可以模糊查询。避免了不走数据库索引的情况

Elasticsearch对模糊搜索非常擅长（搜索速度很快）

从Elasticsearch搜索到的数据可以根据评分过滤掉大部分的，只要返回评分高的给用户就好了（原生就支持排序）

没有那么准确的关键字也能搜出相关的结果（能匹配有相关性的记录）

### 分词

众所周知，世界上有这么多的语言，那Elasticsearch怎么切分这些词呢？，Elasticsearch内置了一些分词器

Standard  Analyzer 。按词切分，将词小写

Simple Analyzer。按非字母过滤（符号被过滤掉），将词小写

WhitespaceAnalyzer。按照空格切分，不转小写

….等等等

Elasticsearch分词器主要由三部分组成：

􏱀􏰉􏰂􏰈􏰂􏰆􏰄Character Filters（文本过滤器，去除HTML）

Tokenizer（按照规则切分，比如空格）

TokenFilter（将切分后的词进行处理，比如转成小写）

### 数据结构

Index：Elasticsearch的Index相当于数据库的Table

Type：这个在新的Elasticsearch版本已经废除（在以前的Elasticsearch版本，一个Index下支持多个Type--有点类似于消息队列一个topic下多个group的概念）

Document：Document相当于数据库的一行记录

Field：相当于数据库的Column的概念

Mapping：相当于数据库的Schema的概念

DSL：相当于数据库的SQL（给我们读取Elasticsearch数据的API）

### 项目架构 

主从集群- 主从分片


## Lucene

站内搜索引擎

将JavaBean对象封装到Document对象中，  
然后通过IndexWriter把document写入到索引库中。  
当用户需要查询的时候，就使用IndexSearcher从索引库中读取数据，  
找到对应的Document对象，  
从而解析里边的内容，再封装到JavaBean对象中让我们使用。

## Shiro 权限认证

1. 重写 AuthorizingRealm 
> 权限认证 protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
> 登录认证 protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
2. 定义过滤器
3. 通过 @RequiresPermissions("sys:fdfs:all") 注解形式进行权限验证，或者@RequiresRoles("admin")，进行角色认证。

## Tomcat  Servlet容器，处理Request请求

### 架构设计 
[架构设计](https://www.jianshu.com/p/2f4a2bca088c)

门面模式
RequestFacade 实现了HttpServletRequest的类

Engine   引擎
    Pileline 阀门
    List<Host>
Host  主机 
    Pileline 阀门
    List<Context>  
Context  应用 
    Pileline 阀门
    List<Wrappeer> Wrapper -> servlet类      
Wrappeer 
    Pileline 阀门
    List<Servlte>
    
## 汉字

汉字在GBK编码中是占两个字节
在UTF-8会占用3或者4个字节（扩展了B区）。

Mysql中的Text类型最大可存储65535字节。
根据汉字的编码就可以判断可以存储多少汉字。

## hashcode
下面代码如果在char类型中，计算结果一样，则hashcode一样，但对象可能不同

    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }