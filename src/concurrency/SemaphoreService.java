package concurrency;

/**
 * @author lijian
 * @description
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
