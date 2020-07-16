package basic.concurrency.utils;

/**
 * @author lijian 信号量
 * @description
 * Semaphore(信号量)实际上就是可以控制同时访问的线程个数，它维护了一组"许可证"。
 *
 * 当调用acquire()方法时，会消费一个许可证。如果没有许可证了，会阻塞起来
 *
 * 当调用release()方法时，会添加一个许可证。
 *
 * 这些"许可证"的个数其实就是一个count变量罢了
 * @date 2020/6/5
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreService {

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    //信号量  可以表示多个线程竞争多个资源
    private Semaphore semaphore = new Semaphore(2);// 同步关键类，构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行制定代码

    public void doSomething() {
        try {
            /**
             * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
             * 因为semaphore的构造方法是2，则同一时刻只允许2个线程进入，其他线程等待。
             * */
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + ":doSomething start-" + getFormatTimeStr());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":doSomething end-" + getFormatTimeStr());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getFormatTimeStr() {
        return sf.format(new Date());
    }
}
