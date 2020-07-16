package basic.concurrency.blockingQueue;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lijian
 * @description 生产者-消费者 阻塞队列形式
 * @date 2020/6/15
 */

class MyResource{
    private  volatile boolean FLAG = true; //默认开启，进行生产加消费
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void Myprod() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.getAndIncrement()+"";
            retValue = blockingQueue.offer(data,2, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t"+"插入队列"+data+"成功" );
            }else {
                System.out.println(Thread.currentThread().getName()+"\t"+"插入队列失败" );
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"大老板叫停了 flag = false，生产动作结束" );
    }

    public void MyConsumer() throws InterruptedException {
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t"+"超过两秒没有取到蛋糕，消费退出" );

                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t"+"消费队列获取蛋糕"+result+"成功" );
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"大老板叫停了 flag = false，生产动作结束" );
    }

    public  void stop(){
        this.FLAG = false;
    }

}


public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new ArrayBlockingQueue(10));

        new Thread( () ->{
            System.out.println(Thread.currentThread().getName()+"\t"+"生产线程启动");
            try {
                myResource.Myprod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"prod").start();

        new Thread( () ->{
            System.out.println(Thread.currentThread().getName()+"\t"+"消费线程启动");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            try {
                myResource.MyConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer 启动").start();

        TimeUnit.SECONDS.sleep(5);
        myResource.stop();
    }
}
