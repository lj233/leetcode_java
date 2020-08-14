 #线程
 
 进程作为资源分配的基本单位
 
 线程作为资源调度的基本单位，是程序的执行单元，执行路径(单线程：一条执行路径，
 多线程：多条执行路径)。是程序使用CPU的最基本单位
 线程有3个基本状态：
 
 执行、就绪、阻塞
 
 线程有5种基本操作：
 
 派生、阻塞、激活、 调度、 结束
 
 
+ 并行：
 
         并行性是指同一时刻内发生两个或多个事件。
         
         并行是在不同实体上的多个事件
 
+  并发：
 
         并发性是指同一时间间隔内发生两个或多个事件。
         
         并发是在同一实体上的多个事件、
         
         由此可见：并行是针对进程的，并发是针对线程的。

 ---
 ## 线程池 
 
 * 顶级接口是Executor  
 
 * newCachedThreadPool 创建一个可根据需要创建新线程的线程池 终止60秒内未使用的线程
 * newFixedThreadPool  创建一个固定线程数的线程池  无界队列，失败会有新线程代替执行
 * newScheduledThreadPool 安排在给定延迟后执行任务或者定期执行
 * newSingleThreadPool  返回一个单例线程，会在失败或关闭后重新启动一个线程执行下去
 
## 线程池参数
  * int corePoolSize,  核心数  理解为今日当值线程， 当线程达到核心数后，会放入缓存队列
  *   int maximumPoolSize,  线程池能容纳的最大线程数  新线程进来并且阻塞队列满了 ，扩容为最大容量
  *   long keepAliveTime,  多余的空闲线程执行时间  线程数大于核心线程数是启用
  *   TimeUnit unit,       时间单位
  *  BlockingQueue<Runnable> workQueue 任务队列
  *  ThreadFactory threadFactory,  线程工厂的标配属性，一般用默认
  *    RejectedExecutionHandler handler   拒绝策略
 
 # 线程池 ThreadPoolExecutor源码介绍
 
 线程数量要点：
 
     如果运行线程的数量少于核心线程数量，则创建新的线程处理请求
     
     如果运行线程的数量大于核心线程数量，小于最大线程数量，则当队列满的时候才创建新的线程
     
     如果核心线程数量等于最大线程数量，那么将创建固定大小的连接池
     
     如果设置了最大线程数量为无穷，那么允许线程池适合任意的并发数量
 
 线程空闲时间要点：
 
     当前线程数大于核心线程数，如果空闲时间已经超过了，那该线程会销毁。
 
 排队策略要点：
 
     同步移交：不会放到队列中，而是等待线程执行它。如果当前线程没有执行，很可能会新开一个线程执行。
     
     无界限策略：如果核心线程都在工作，该线程会放到队列中。所以线程数不会超过核心线程数
     
     有界限策略：可以避免资源耗尽，但是一定程度上减低了吞吐量
 
 当线程关闭或者线程数量满了和队列饱和了，就有拒绝任务的情况了：
 
 拒绝任务策略：
 
     直接抛出异常
     
     使用调用者的线程来处理
     
     直接丢掉这个任务
     
     丢掉最老的任务
     
 ## execute(runnable) 方法 
     在源码上分三步走
 ## shutdown（） 方法
     调用shutdown()后，线程池状态立刻变为SHUTDOWN，而调用shutdownNow()，线程池状态立刻变为STOP。
     
     shutdown()等待任务执行完才中断线程，而shutdownNow()不等任务执行完就中断了线程。

 ## 线程生命状态

 * 线程的生命状态 new Runnable running Blocked dead
 ## 阻塞

 * 阻塞分为3种   
 > 等待队列阻塞 Thread.wait()   
 同步阻塞 running的线程获取同步锁失败——》锁池 lock pool中  
 其他阻塞  sleep（） join方法
 
 ## 终止线程的方式
  * 使用 volatile退出标志 ，保证同一时刻只能有一个线程做修改
  * interrupt()阻塞下抛出异常后break退出、运行时将中断标识设置为true，方便isInterrupt()判断
  * stop()线程不安全 ：创建子线程的线程抛出错误并且释放子线程的所有锁
  
## sleep()与wait()区别

 * sleep()属于Thread方法，不会释放锁，wait属于Object方法，需要notify唤醒
 
 ## start()和run()的区别

 * start()真正实现了多线程运行，不需要等待run方法体运行完就可以执行后面的代码
 * run（）是方法体，方法结束，表示线程终止
 
 run()和start()方法区别：
 
 run():仅仅是封装被线程执行的代码，直接调用是普通方法
 
 start():首先启动了线程，然后再由jvm去调用该线程的run()方法

---
# java锁

   * 乐观锁：读不上锁，写会比较，实现方式cas，非阻塞，会不断获取锁
   * 悲观锁： 读写都上锁。synchronized ,java锁框架AQS先尝试乐观锁，后才是悲观锁
   * 自旋锁： 获取不到锁的线程会不断尝试、这个时间应小于CPU上下文切换的时间
   * Synchronized同步锁，可重入锁（同一线程自动对同一对象或者Class持有锁）
        * 作用范围
            * 作用在方法上时，锁住的是实例的对象this
            * 作用在静态方法时 ，锁住的是Class对象
        *  核心组件
            + Wait Set：哪些调用 wait 方法被阻塞的线程被放置在这里；
            + Contention List：竞争队列，所有请求锁的线程首先被放在这个竞争队列中；
            + Entry List：Contention List 中那些有资格成为候选资源的线程被移动到 Entry List 中；
            + OnDeck：任意时刻，最多只有一个线程正在竞争锁资源，该线程被成为 OnDeck；
            + Owner：当前已经获取到所资源的线程被称为 Owner；
            + !Owner：当前释放锁的线程。


# ReentrantLock

        1. void lock(): 执行此方法时, 如果锁处于空闲状态, 当前线程将获取到锁. 相反, 如果锁已经
        被其他线程持有, 将禁用当前线程, 直到当前线程获取到锁.
        2. boolean tryLock()：如果锁可用, 则获取锁, 并立即返回 true, 否则返回 false. 该方法和
        lock()的区别在于, tryLock()只是"试图"获取锁, 如果锁不可用, 不会导致当前线程被禁用, 
        当前线程仍然继续往下执行代码. 而 lock()方法则是一定要获取到锁, 如果锁不可用, 就一
        直等待, 在未获得锁之前,当前线程并不继续向下执行. 
        3. void unlock()：执行此方法时, 当前线程将释放持有的锁. 锁只能由持有者释放, 如果线程
        并不持有锁, 却执行该方法, 可能导致异常的发生.
        4. Condition newCondition()：条件对象，获取等待通知组件。该组件和当前的锁绑定，
        当前线程只有获取了锁，才能调用该组件的 await()方法，而调用后，当前线程将缩放锁。


        
### 区别 
        1. ReentrantLock 通过方法 lock()与 unlock()来进行加锁与解锁操作，与 synchronized 会
        被 JVM 自动解锁机制不同，ReentrantLock 加锁后需要手动进行解锁。为了避免程序出
        现异常而无法正常解锁的情况，使用 ReentrantLock 必须在 finally 控制块中进行解锁操
        作。
        2. ReentrantLock 相比 synchronized 的优势是可中断、公平锁、多个锁。这种情况下需要
        使用 ReentrantLock。
        

 
 ## 非公平锁 
           JVM 按随机、就近原则分配锁的机制则称为不公平锁，ReentrantLock 在构造函数中提供了
          是否公平锁的初始化方式，默认为非公平锁。非公平锁实际执行的效率要远远超出公平锁，除非
          程序有特殊需要，否则最常用非公平锁的分配机制。
 ## 公平锁
         
         公平锁指的是锁的分配机制是公平的，通常先对锁提出获取请求的线程会先被分配到锁，
         ReentrantLock 在构造函数中提供了是否公平锁的初始化方式来定义公平锁。
 
 ## 读写锁 
         读锁
         如果你的代码只读数据，可以很多人同时读，但不能同时写，那就上读锁
         写锁
         如果你的代码修改数据，只能有一个人在写，且不能同时读取，那就上写锁。总之，读的时候上
         读锁，写的时候上写锁！
         Java 中读写锁有个接口 java.util.concurrent.locks.ReadWriteLock ，也有具体的实现
         ReentrantReadWriteLock
 
 ## 共享锁和独占锁
         独占锁
         独占锁模式下，每次只能有一个线程能持有锁，ReentrantLock 就是以独占方式实现的互斥锁。
         独占锁是一种悲观保守的加锁策略，它避免了读/读冲突，如果某个只读线程获取锁，则其他读线
         程都只能等待，这种情况下就限制了不必要的并发性，因为读操作并不会影响数据的一致性。
         共享锁
         共享锁则允许多个线程同时获取锁，并发访问 共享资源，如：ReadWriteLock。共享锁则是一种
         乐观锁，它放宽了加锁策略，允许多个执行读操作的线程同时访问共享资源。

## 重量级锁 mutex Lock

        Synchronized 效率低的原因-底层依赖于操作系统 Mutex Lock 所实现的锁我们称之为
        “重量级锁”。
        
## 轻量级锁 
        锁的状态总共有四种：无锁状态、偏向锁、轻量级锁和重量级锁。
        锁升级
        随着锁的竞争，锁可以从偏向锁升级到轻量级锁，再升级的重量级锁（但是锁的升级是单向的，
        也就是说只能从低到高升级，不会出现锁的降级）。
        “轻量级”是相对于使用操作系统互斥量来实现的传统锁而言的。但是，首先需要强调一点的是，
        轻量级锁并不是用来代替重量级锁的，它的本意是在没有多线程竞争的前提下，减少传统的重量
        级锁使用产生的性能消耗。在解释轻量级锁的执行过程之前，先明白一点，轻量级锁所适应的场
        景是线程交替执行同步块的情况，如果存在同一时间访问同一锁的情况，就会导致轻量级锁膨胀
        为重量级锁。

## 偏向锁 

        Hotspot 的作者经过以往的研究发现大多数情况下锁不仅不存在多线程竞争，而且总是由同一线
        程多次获得。偏向锁的目的是在某个线程获得锁之后，消除这个线程锁重入（CAS）的开销，看起
        来让这个线程得到了偏护。引入偏向锁是为了在无多线程竞争的情况下尽量减少不必要的轻量级
        锁执行路径，因为轻量级锁的获取及释放依赖多次 CAS 原子指令，而偏向锁只需要在置换
        ThreadID 的时候依赖一次 CAS 原子指令（由于一旦出现多线程竞争的情况就必须撤销偏向锁，所
        以偏向锁的撤销操作的性能损耗必须小于节省下来的 CAS 原子指令的性能消耗）。上面说过，轻
        量级锁是为了在线程交替执行同步块时提高性能，而偏向锁则是在只有一个线程执行同步块时进
        一步提高性能。
 
## 分段锁  ConcurrentHashMap 
锁住的是segment，最大支持16个线程并发

## java中的阻塞队列


            1. ArrayBlockingQueue ：由数组结构组成的有界阻塞队列。
            2. LinkedBlockingQueue ：由链表结构组成的有界阻塞队列。最大值为21亿多。
            3. PriorityBlockingQueue ：支持优先级排序的无界阻塞队列。
            4. DelayQueue：使用优先级队列实现的无界阻塞队列。
            5. SynchronousQueue：不存储元素的阻塞队列。
            6. LinkedTransferQueue：由链表结构组成的无界阻塞队列。
            7. LinkedBlockingDeque：由链表结构组成的双向阻塞队列
            
## volatile 

    变量可见性和禁止指令重排  因为它直接存放在内存中，不需要拷贝到cpu内存
    可以用在中断标志中。

## 两个线程共享数据 
            将数据冲向为类，并且对数据的操作封装为Synchronized方法。
  
##  ThreadLocal          
 * 1、Thread中有一个map，就是ThreadLocalMap
 
 * 2、ThreadLocalMap的key是ThreadLocal，值是我们自己设定的
 * 3、提供了映射， ThreadLocalMap的Entry数组根据了  ThreadLocal的AtomicInteger的 nextHashCode 计算数组存在的值，扩容为*2/3
 
 ## CountDownLatch和CyclicBarrier区别：
     1.countDownLatch是一个计数器，线程完成一个记录一个，计数器递减，只能只用一次 ’
     countDown()计数-1.awit（）等待区  
     Sync extends AbstractQueuedSynchronizer 静态内部类Sync继承了AQS，维护了state状态，
     tryAcquireShared加锁、tryReleaseShared释放锁
     
     2.CyclicBarrier的计数器更像一个阀门，需要所有线程都到达，然后继续执行，计数器递增，提供reset功能，可以多次使用
     提供reset（）方法反复使用，awit()为等待节点，使count--，为零时打开屏障
     3.CountDownLatch 简单的说就是一个线程等待，直到他所等待的其他线程都执行完成并且调用 countDown()方法发出通知后，当前线程才可以继续执行。
    
     4.cyclicBarrier 是所有线程都进行等待，直到所有线程都准备好进入 await()方法之后，所有线程同时开始执行！
    
     5.CountDownLatch 的计数器只能使用一次。而 CyclicBarrier 的计数器可以使用 reset() 方法重置。所以 CyclicBarrier 能处理更为复杂的业务场景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次。

## Semaphore 

    实现了AQS中的 共享锁 ，Fair->Sync->AQS 重写了加锁、解锁方法

    /**
     * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
     * 因为semaphore的构造方法是2，则同一时刻只允许2个线程进入，其他线程等待。
     * */

    是 synchronized 的加强版，作用是控制线程的并发数量。
    abstract static class Sync extends AbstractQueuedSynchronizer Sync实现加锁和释放锁
    构造方法可选公平锁和非公平锁

# cas 
+ 来源维基百科：

    比较并交换(compare and swap, CAS)，是原子操作的一种，可用于在多线程编程中实现不被打断的数据交换操作，
    从而避免多线程同时改写某一数据时由于执行顺序不确定性以及中断的不可预知性产生的数据不一致问题。
     该操作通过将内存中的值与指定数据进行比较，当数值一样时将内存中的数据替换为新的值。

+ CAS有3个操作数：

    内存值V
    
    旧的预期值A
    
    要修改的新值B
    
当多个线程尝试使用CAS同时更新同一个变量时，

    只有其中一个线程能更新变量的值(A和内存值V相同时，将内存值V修改为B)，而其它线程都失败，
    失败的线程并不会被挂起，不断获取最新值更新为期望值，自旋
    而是被告知这次竞争中失败， 并可以再次尝试(或者什么都不做)

## Unsafe (CAS)的方法最终由cpu执行C语言，所以是原子性的
    
    // 第一和第二个参数代表对象的实例以及地址，第三个参数代表期望值，第四个参数代表更新值
    public final native boolean compareAndSwapObject(Object var1, long var2, Object var4, Object var5);
    
    public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
    
    public final native boolean compareAndSwapLong(Object var1, long var2, long var4, long var6);
    从原理上概述就是：Atomic包的类的实现绝大调用Unsafe的方法，
    而Unsafe底层实际上是调用C代码，C代码调用汇编，最后生成出一条CPU指令cmpxchg，完成操作。
    这也就为啥CAS是原子性的，因为它是一条CPU指令，不会被打断
  
### cas的ABA 问题 

AtomicStampedReference 维护了一个stamp时间戳

在这里我们会发现Pair里面只是保存了值reference和时间戳stamp。

在compareAndSet方法中最后还调用了casPair方法，从名字就可以看到，主要是使用CAS机制更新新的值reference和时间戳stamp。  
    
# 线程中断

interrupt() 方法 只是设置了一个标识位，是否结束要由线程自己决定、如果线程处于阻塞中，不会设置标识位、会抛出异常

interrupt线程中断还有另外两个方法(检查该线程是否被中断)：

静态方法interrupted()-->会清除中断标志位 调用了实例方法isInterrupted(true)

实例方法isInterrupted()-->不会清除中断标志位

# AQS 
 + JUC 包下的 AbstractQueuedSynchronizer
 + 维护了一个Node(CLH队列,先进先出)节点先进先出队列和 ，加锁的时候，要么获取到锁，要么把等待的线程加入到等待队列
 + 维护了 state  cas方法更改 state的值 0为无锁，1为有锁，共享锁可大于一
 + AQS其实就是一个可以给我们实现锁的框架
 
 + 内部实现的关键是：先进先出的队列、state状态
 
 + 定义了内部类ConditionObject
 
 + 拥有两种线程模式
 
    独占模式  需要子类实现 tryAcquire(int) 和 tryRelease(int)    ReentrantLock
 
    共享模式  tryAcquireShared(int)  和 tryReleaseShared(int)   CountDownLatch和Semaphore
 
 + 在LOCK包中的相关锁(常用的有ReentrantLock、 ReadWriteLock)都是基于AQS来构建
 
 ReentrantLock中的子类Sync实现了AQS，又在Fair和NonFair中重写了tryAcquire方法。   
 
 + 一般我们叫AQS为同步器
 
 ## 方法
    
    过程：获取独占锁 acquire(int)尝试获取资源，如果获取失败，将线程插入等待队列。
    插入等待队列后，acquire(int)并没有放弃获取资源，
        // 子类必须重写此方法，否则会抛出异常
        protected boolean tryAcquire(long arg) {
            throw new UnsupportedOperationException();
        }
    而是根据前置节点状态状态判断是否应该继续获取资源，
    如果前置节点是头结点，继续尝试获取资源，
    如果前置节点是SIGNAL状态，就中断当前线程，
    否则继续尝试获取资源。直到当前线程被park()或者获取到资源，acquire(int)结束
    
    
    过程:释放独占锁，首先调用子类的tryRelease(如果子类没有重写，则直接抛出异常)方法释放锁,然后唤醒后继节点,
    在唤醒的过程中,需要判断后继节点是否满足情况,
    如果后继节点不为且不是作废状态,则唤醒这个后继节点,
    否则从tail节点向前寻找合适的节点,如果找到,则唤醒.
    
    
## 死锁
    当前线程拥有其他线程需要的资源
    
    当前线程等待其他线程已拥有的资源
    
    都不放弃自己拥有的资源
+ 是一种可能情况 同时永久等待，造成死锁
+ 如果所有程序以固定的顺序获取锁，就可以避免死锁问题
[线程死锁的情况](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484218&idx=1&sn=5e5d7859627ed2c30ee517cb64e0a930&chksm=ebd7423bdca0cb2d55528781e9d3d12cfb94bc566946069293d1fad3c788a7e617879ba66b9e&scene=21###wechat_redirect)

避免死锁可以概括成三种方法：
    
    固定加锁的顺序(针对锁顺序死锁)
    
    开放调用(针对对象之间协作造成的死锁)
    
    使用定时锁-->tryLock()
    
    如果等待获取锁时间超时，则抛出异常而不是一直等待
死锁检测

    jconsole
    jps->jstack
## @guardedBy

    1、@GuardedBy( "this" ) 受对象内部锁保护
    2、@GuardedBy( "fieldName" ) 受 与fieldName引用相关联的锁 保护。
    3、@GuardedBy( "ClassName.fieldName" ) 受 一个类的静态field的锁 保存。
    4、@GuardedBy( "methodName()" ) 锁对象是 methodName() 方法的返值，受这个锁保护。
    5、@GuardedBy( "ClassName.class" ) 受 ClassName类的直接锁对象保护。而不是这个类的某个实例的锁对象