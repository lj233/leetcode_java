package QueueAndStack.test;

/**
 * @author lijian
 * @description 循环队列移动平均数问题
 * @date 2019/10/19
 * <p>
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * <p>
 * For example,
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class CycleQueueTest {
    public static void main(String[] args) {
        MovingAverage m = new MovingAverage(3);
        double d1 = m.next(1);
        double d2 = m.next(10);
        double d3 = m.next(3);
        double d4 = m.next(5);
        System.out.println("1:" + d1);
        System.out.println("2:" + d2);
        System.out.println("3:" + d3);
        System.out.println("4:" + d4);
    }

}

class MovingAverage {

    int head;
    int tail;
    int size;
    double sum;
    int[] data;

    public MovingAverage(int k) {
        data = new int[k];
        head = -1;
        tail = -1;
        sum = 0;
        size = k;
    }

    public double next(int x) {
        if (isFull()) {
            sum -= getPop();
            sum += x;
            deQueue();
            enQueue(x);
            return sum / ((tail - head + size) % size + 1);
        } else {
            enQueue(x);
            return (sum += x) / ((tail - head + size) % size + 1);
        }

    }


    public Boolean enQueue(int x) {
        if (isFull() == true) {
            return false;
        }
        if (isEmpty() == true) {
            head = 0;
        }
        tail = (tail + 1) % size;
        data[tail] = x;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        //队列为空的情况
        if (head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % size;
        return true;
    }

    public int getPop() {
        return data[head];
    }


    public Boolean isEmpty() {
        if (head == -1) {
            return true;
        }
        return false;
    }

    public Boolean isFull() {
        if ((tail + 1) % size == head) {
            return true;
        }
        return false;
    }
}