package basic.concurrency.threadpool;

/**
 * @author lijian
 * @description
 * @date 2020/6/16
 * (int corePoolSize,  核心数  理解为今日当值线程， 当线程达到核心数后，会放入缓存队列
 *   int maximumPoolSize,  线程池能容纳的最大线程数  新线程进来并且阻塞队列满了 ，扩容为最大容量
 *   long keepAliveTime,  多余的空闲线程执行时间  线程数大于核心线程数是启用
 *   TimeUnit unit,       时间单位
 * BlockingQueue<Runnable> workQueue 任务队列
 * ThreadFactory threadFactory,  线程工厂的标配属性，一般用默认
 *    RejectedExecutionHandler handler   拒绝策略
 */
public class ThreadPoolExecutorTest {
}
