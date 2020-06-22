package concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author lijian
 * @description
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
        });

        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName());
                cyclicBarrier.await();
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
