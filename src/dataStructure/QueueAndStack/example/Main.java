package dataStructure.QueueAndStack.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 队列的实现
 * 删除元素后移动头指针
 * 利用头指针的位置获取队列的实际长度
 * 造成了资源的浪费，应该是一个循环队列
 */
public class Main {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.addQueue(3, 4, 5, 6, 7, 8, 9, 10);
        queue.deQueue(queue.getP_start());
        queue.deQueue(queue.getP_start());
        queue.deQueue(queue.getP_start());
        queue.deQueue(queue.getP_start());
        for (int z : queue.getMyQueue().subList(queue.getP_start(), queue.getMyQueue().size())) {
            System.out.println(z);
        }
    }
}

class MyQueue {

    private List<Integer> queue;

    int p_start = 0;

    public MyQueue() {
        queue = new ArrayList<Integer>();
        p_start = 0;
    }

    public void addQueue(int... x) {
        for (int y : x) {
            queue.add(y);
        }
        System.out.println("length:" + queue.size());
    }

    public Boolean deQueue(int y) {
        if (queue.size() == 0) {
            return false;
        }
        p_start++;
        System.out.println("head:" + p_start);
        return true;
    }

    public List<Integer> getMyQueue() {
        return queue;
    }

    public int getP_start() {
        return p_start;
    }

}