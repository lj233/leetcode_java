## Zookeeper 概念  最终一致性的分布式数据库，节点集群可见




        Zookeeper 是一个分布式协调服务，可用于服务发现，分布式锁，分布式领导选举，配置管理等。
             Zookeeper 提供了一个类似于 Linux 文件系统的树形结构（可认为是轻量级的内存文件系统，但
             只适合存少量信息，完全不适合存储大量文件或者大文件），同时提供了对于每个节点的监控与
             通知机制。

### 加载
+ 监听2181
+ 选举机制
+ 加载内存

### 作用
+ 统一配置管理 
    将公共配置放到父节点中，子节点监听变化
+ 统一命名服务 访问节点拿到ip数据
+ 分布式锁 
    访问的时候会创建带顺序号的临时/短暂(EPHEMERAL_SEQUENTIAL)节点，  
    比如，系统A创建了id_000000节点，系统B创建了id_000002节点，系统C创建了id_000001节点。

    接着，拿到/locks节点下的所有子节点(id_000000,id_000001,id_000002)，判断自己创建的是不是最小的那个节点
    
    如果是，则拿到锁。
    
    释放锁：执行完操作后，把创建的节点给删掉
    
    如果不是，则监听比自己要小1的节点变化
    
+ 集群管理 监听临时节点
 
      配置  zoo.cfg: 
        server localhost:2181:3887(选举端口)     
     
> 维护了Znode  

     （有序的）短暂的/（有序的）长久的    根据客户端和服务器端断开连接后，是否会自动删除 
     
> 监听器
 查看节点的数据变化和增减变化     

## Watcher

zk的watcher由客户端、客户端watchManager和zk服务器组成，zk客户端向zk服务器注册watcher的同时，  
会将watcher对象存储在客户端的watcherManager，zk服务器触发watcher事件后，会向客户端发送通知，  
客户端线程从watcher manager中取出对应的watcher对象，执行相应的回调逻辑。


client注册的watcher和server注册的watcher有什么区别  
作用和类型有区别  
client注册的watcher类型没有限制,作用就是说client监控到xx事件后干的事情，比如重新获取数据  
server注册的watcher都是ServerCnxn类型，作用就是告诉对应client 发生了xx WatchedEvent就行  
由于watcher并没有直接在网络进行传输，所以两者并不一样  

server怎么知道一个WatchedEvent触发，要通知哪些client  
server的watch是ServerCnxn，保持了和Client的对话，直接回调process就行了  
都是ServerCnxn(实现了Watcher)的功劳  

watcher 特性总结  
轻量  
WatcherEvent 是 ZooKeeper 整个 Watcher 通知机制的最小通知单元，这个数据结构中只包含三部分内容：通知状态、事件类型和节点路径。  
也就是说，Watcher 通知非常简单，只会告诉客户端发生了事件，而不会说明事件的具体内容。例如针对 NodeDataChanged 事件，ZooKeeper 的Watcher   
只会通知客户端指定数据节点的数据内容发生了变更，而对于原始数据以及变更后的新数据都无法从这个事件中直接获取到，  
而是需要客户端主动重新去获取数据——这也是 ZooKeeper 的 Watcher 机制的一个非常重要的特性。  
客户端向服务端注册 Watcher 的时候，并不会把客户端真实的 Watcher 对象传递到服务端，仅仅只是在客户端请求中使用 boolean 类型属性进行了标记，  
同时服务端也仅仅只是保存了当前连接的 ServerCnxn 对象。这样轻量级的 Watcher 机制设计，在网络开销和服务端内存开销上都是非常廉价的。

一次性
无论是服务端还是客户端，一旦一个 Watcher 被触发，ZooKeeper 都会将其从相应的存储中移除。  
因此，在 Watcher 的使用上，需要反复注册。这样的设计有效地减轻了服务端的压力,如果注册一个 Watcher 之后一直有效，  
那么针对那些更新非常频繁的节点，服务端会不断地向客户端发送事件通知，这无论对于网络还是服务端性能的影响都非常大。

客户端串行执行
客户端 Watcher 回调的过程是一个串行同步的过程，这为我们保证了顺序，


###选举流程简述
  
  + leader
  + 过半机制  
  + 2PC
  + 同步消息  sendQueue，recvQueue
  
1. 投给自己
2. 接收其他服务器选票
3. pk 
4. 投票  
5. 统计  
  
  
  eg: 目前有5台服务器，每台服务器均没有数据，它们的编号分别是1,2,3,4,5,按编号依次启动，它们的选择举过程如下：
  
      服务器1启动，给自己投票，然后发投票信息，由于其它机器还没有启动所以它收不到反馈信息，服务器1的状态一直属于Looking(选举状态)。
      服务器2启动，给自己投票，同时与之前启动的服务器1交换结果，由于服务器2的编号大所以服务器2胜出，但此时投票数没有大于半数，所以两个服务器的状态依然是LOOKING。
      服务器3启动，给自己投票，同时与之前启动的服务器1,2交换信息，由于服务器3的编号最大所以服务器3胜出，此时投票数正好大于半数，所以服务器3成为领导者，服务器1,2成为小弟。
      服务器4启动，给自己投票，同时与之前启动的服务器1,2,3交换信息，尽管服务器4的编号大，但之前服务器3已经胜出，所以服务器4只能成为小弟。
      服务器5启动，后面的逻辑同服务器4成为小弟
    
  维护了共同的投票箱

### 节点 四种类型的节点的创建方式

 
PERSISTENT  
[zk: localhost:2181(CONNECTED) 1] create /jin/y1 ""  
Created /jin/y1  

PERSISTENT_SEQUENTIAL  
[zk: localhost:2181(CONNECTED) 2] create -s /jin/y2 ""  
Created /jin/y20000000002  

EPHEMERAL  
[zk: localhost:2181(CONNECTED) 3] create -e /jin/y3 ""  
Created /jin/y3  

EPHEMERAL_SEQUENTIAL   
Command failed: java.lang.IllegalArgumentException: Path must start with / character  
[zk: localhost:2181(CONNECTED) 4] create -s -e /jin/y4 ""  


 
  
## 脑裂  
  假死：由于心跳超时（网络原因导致的）认为master死了，但其实master还存活着。
  脑裂：由于假死会发起新的master选举，选举出一个新的master，但旧的master网络又通了，导致出现了两个master ，有的客户端连接到老的master 有的客户端链接到新的master。

    
## 2PC
1. prepare 占用资源  leader发送日志，follower准备持久化
2. 等待ack           ack过半，发送提交
3. commit/rollback  follower提交日志
 
 ## 持久化
 
 日志+快照
 
 
 事务性请求：create，set。delete
 非事务性请求：get，exist
 
## ZAB协议的核心机制

其核心机制是定义了对于那些会改变Zookeeper服务器数据状态的事务请求的处理方式，即：

所有的事务请求必须由一个全局唯一的服务器来协调处理，这样的服务器称为Leader服务器，  
而余下的其他服务器是Follower（这里暂不说Observer，因为不参与投票）。Leader服务器复杂将一个客户端的事务请求转换成一个事务提议（Proposal），  
并将该Proposal分发给集群中所有的Follower服务器。之后Leader服务器需要等待所有Follower服务器的反馈，  
一旦超过半数的Follower服务器进行了正确地反馈后，那么Leader就会再次向所有的Follower服务器分发Commit消息，要求其将前一个Proposal进行提交