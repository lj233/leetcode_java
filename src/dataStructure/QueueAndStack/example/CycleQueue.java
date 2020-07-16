package dataStructure.QueueAndStack.example;

/**
 * @author lijian
 * @description 循环队列的实现
 * 队列为空时font=tail,队列满了时 font=tail
 * 为区分这种情况，当tail+1=maxSize时就说循环队列满了，
 * 因此队列满了的条件是（tail+1）% maxSize = head
 * 最后打印队列中的元素从tail开始，(tail+1)% maxSize == 结束
 * @date 2019/10/19
 */
public class CycleQueue {
    private int[] data;
    private int head;
    private int tail;
    private int size;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public CycleQueue(int k) {
        data = new int[k];
        head = -1;
        tail = -1;
        size = k;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (isFull() == true) {
            return false;
        }
        if (isEmpty() == true) {
            head = 0;
        }
        tail = (tail + 1) % size;
        data[tail] = value;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        if (head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % size;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (isEmpty() == true) {
            return -1;
        }
        return data[head];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (isEmpty() == true) {
            return -1;
        }
        return data[tail];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return head == -1;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return ((tail + 1) % size) == head;
    }
}

