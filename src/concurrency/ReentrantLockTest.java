package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijian
 * @description
 * @date 2020/1/6
 * <p>
 * 1. ReentrantLock 通过方法 lock()与 unlock()来进行加锁与解锁操作，与 synchronized 会
 * 被 JVM 自动解锁机制不同，ReentrantLock 加锁后需要手动进行解锁。为了避免程序出
 * 现异常而无法正常解锁的情况，使用 ReentrantLock 必须在 finally 控制块中进行解锁操
 * 作。
 * 2. ReentrantLock 相比 synchronized 的优势是可中断、公平锁、多个锁。这种情况下需要
 * 使用 ReentrantLock。
 */
public class ReentrantLockTest {



    public static void main(String[] args) {
         Lock lock = new ReentrantLock();
        //Lock lock=new ReentrantLock(true);//公平锁
        //Lock lock=new ReentrantLock(false);//非公平锁
         Condition condition = lock.newCondition();//创建 Condition
        new ReentrantLockTest().testMethod(lock,condition);
    }

    public void testMethod(Lock lock,Condition condition) {
        try {
            boolean flag = lock.tryLock();//lock 加锁
            System.out.println(flag);
            //1：wait 方法等待：
            //System.out.println("开始 wait");
            condition.await();
            //通过创建 Condition 对象来使线程 wait，必须先执行 lock.lock 方法获得锁
            //:2：signal 方法唤醒
            condition.signal();//condition 对象的 signal 方法可以唤醒 wait 线程
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName() + (" " + (i + 1)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //获取锁后必须要释放锁，获取几次就释放几次
            lock.unlock();
        }
    }

}
