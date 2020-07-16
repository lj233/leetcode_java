package basic.concurrency.threadpool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lijian
 * @description ThreadLocal用法
 * @date 2020/7/6
 */
public class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }

                @Override
                public void set(Integer value) {
                    super.set(value);
                }
            };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        System.out.println("get() = " + get());
    }
}
