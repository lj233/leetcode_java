package basic.concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lijian
 * @description 读写锁
 * @date 2020/1/6
 */
public class WriteReadLockTest {
    public static void main(String[] args) {
        final MyTask myTask = new MyTask();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                myTask.read();
            }
        });
        t1.setName("t1");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                myTask.write();
            }
        });
        t2.setName("t2");

        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }
}

class MyTask {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}

