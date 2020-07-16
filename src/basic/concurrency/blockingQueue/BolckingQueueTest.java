package basic.concurrency.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lijian
 * @description
 * @date 2020/6/15
 */
public class BolckingQueueTest {
    //有界阻塞队列，数组结构
    private ArrayBlockingQueue arrayBlockingQueue;
    //有界阻塞队列，链表结构 默认范围为 Integer.max_value  21亿
    private LinkedBlockingQueue linkedBlockingQueue;
    //不存储元素，只维护一个（生产者消费者）
    private SynchronousQueue synchronousQueue;

    public static void main(String[] args) {

        BlockingQueue<String> strings = new ArrayBlockingQueue<>(3);
        // 增加
        System.out.println("strings.add(\"超出队列大小会抛异常\") = " + strings.add("超出队列大小会抛异常"));

        System.out.println("strings.offer(\"超出队列大小会返回false\") = " + strings.offer("超出队列大小会返回false"));

        try {
            strings.put("超出队列大小会阻塞");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("strings.offer(\"超出队列大小会阻塞一段时间\", 2, TimeUnit.SECONDS) = " + strings.offer("超出队列大小会阻塞一段时间", 2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        //取出
        System.out.println("strings.poll() = " + strings.poll());


        try {
            System.out.println("strings.take()队列为空会阻塞 = " + strings.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("strings.poll(2,TimeUnit.SECONDS) = " + strings.poll(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("strings.peek() = " + strings.peek());
    }
}
