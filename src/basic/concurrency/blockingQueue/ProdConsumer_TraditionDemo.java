package basic.concurrency.blockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijian
 * @description 生产者消费者们模式
 * @date 2020/6/15
 * <p>
 * 题目 A打印5次  B10次 C 15次
 */
class ShareData {
    private volatile int num = 1;

    //ReentrantLock  Condition 锁

    private Lock lock = new ReentrantLock();   // 默认是非公平锁（可能被新线程加塞） 传入true 实现公平锁，维护AQS队列

    private Condition condition = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
//        lock.lockInterruptibly();   可以被 new Thread().interrupt(); 打断  实现可中断

        try {
            while (num != 1) {
                condition.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            //多线程，状态位特别重要
            num = 2;
            // 通知线程2唤醒
            condition2.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }


    }

    public void print10() {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            num = 3;
            condition3.signal();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }


    }

    public void print15() {
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 1;
            condition.signal();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }


    }


}


public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            shareData.print5();
        }, "A").start();

        new Thread(() -> {
            shareData.print10();
        }, "B").start();


        new Thread(() -> {
            shareData.print15();
        }, "C").start();
    }
}
