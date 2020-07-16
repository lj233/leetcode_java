package basic.concurrency.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author lijian 栅栏
 * @description
 *
 * 简单来说：CyclicBarrier允许一组线程互相等待，直到到达某个公共屏障点。
 * 叫做cyclic是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用(对比于CountDownLatch是不能重用的)
 *
 * 使用说明：
 *
 * CountDownLatch注重的是等待其他线程完成，CyclicBarrier注重的是：
 * 当线程到达某个状态后，暂停下来等待其他线程，所有线程均到达以后，继续执行
 * @date 2020/6/15
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("33");
            System.out.println("###################################################");
        });

        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName());
                cyclicBarrier.await();
                System.out.println("1 唤醒执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"1").start();
        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName());
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"2").start();
        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName());
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"3").start();

    }
}
