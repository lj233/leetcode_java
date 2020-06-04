package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lijian
 * @description 高并发的情况下，i++无法保证原子性，往往会出现问题，所以引入AtomicInteger类。
 * @date 2020/6/4
 */
public class AtomicIntegerTest {

    private static final int THREADS_COUNT = 2;

    public static int count = 0;
    public static volatile int countVolatile = 0;  //不能保证原子性，可能两个线程同时拿到主内存中的值，可用Synchronizedv保证原子性
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void increase() {
        count++;
        countVolatile++;
        atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        //以高并发的形式进行2000个数的增加
        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i = 0; i< threads.length; i++) {
            threads[i] = new Thread(() -> {
                for(int i1 = 0; i1 < 1000; i1++) {
                    increase();
                }
                countDownLatch.countDown();
            });
            threads[i].start();
        }

        countDownLatch.await();

        System.out.println(count);
        System.out.println(countVolatile);
        System.out.println(atomicInteger.get());
    }
}