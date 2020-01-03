 #线程
 ---
 ## 线程池 
 * 顶级接口是Executor
 * newCachedThreadPool 创建一个可根据需要创建新线程的线程池 终止60秒内未使用的线程
 * newFixedThreadPool  创建一个固定线程数的线程池  无界队列，失败会有新线程代替执行
 * newScheduledThreadPool 安排在给定延迟后执行任务或者定期执行
 * newSingleThreadPool  返回一个单例线程，会在失败或关闭后重新启动一个线程执行下去
 ## 线程生命状态
 ---
 * 线程的生命状态 new Runnable running Blocked dead
 ## 阻塞
 ---
 * 阻塞分为3种 等待队列阻塞 Thread.wait() 同步阻塞 running的线程获取同步锁失败——》锁池 lock pool中  其他阻塞  sleep（） join方法
 
 ## 终止线程的方式
  * 使用 volatile退出标志 ，保证同一时刻只能有一个线程做修改
  * interrupt()阻塞下抛出异常后break退出、运行时将中断标识设置为true，方便isInterrupt()判断
  * stop()线程不安全 ：创建子线程的线程抛出错误并且释放子线程的所有锁
  
## sleep()与wait()区别
---
 * sleep()属于Thread方法，不会释放锁，wait属于Object方法，需要notify唤醒
 
 ## start()和run()的区别
 ---
 * start()真正实现了多线程运行，不需要等待run方法体运行完就可以执行后面的代码
 * run（）是方法体，方法结束，表示线程终止
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

   
