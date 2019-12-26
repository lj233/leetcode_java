package concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lijian
 * @description 线程出创建线程
 * 顶级接口是Executor
 * newCachedThreadPool 创建一个可根据需要创建新线程的线程池 终止60秒内未使用的线程
 * newFixedThreadPool  创建一个固定线程数的线程池  无界队列，失败会有新线程代替执行
 * newScheduledThreadPool 安排在给定延迟后执行任务或者定期执行
 * newSingleThreadPool  返回一个单例线程，会在失败或关闭后重新启动一个线程执行下去
 *线程的生命状态 new Runnable running Blocked dead
 *
 * 阻塞分为3种 等待队列阻塞 Thread.wait() 同步阻塞 running的线程获取同步锁失败——》锁池 lock pool中  其他阻塞  sleep（） join方法
 * @date 2019/12/26
 */
public class MyExecutor {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        while (true){
//            executorService.execute(()-> System.out.println(Thread.currentThread().getName() + " is running .."));
//        }
        while (true){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " is running ..");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
