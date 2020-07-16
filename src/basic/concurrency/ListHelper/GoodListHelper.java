package basic.concurrency.ListHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lijian
 * @description
 * @date 2020/7/1
 */
public class GoodListHelper<E> implements Runnable{
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    GoodListHelper<Integer> integer;



    public boolean putIfAbsent(E x) {
        // 保证了线程的安全
        synchronized (list) {
            boolean contains = list.contains(x);
            if (!contains) {
                list.add(x);
            }
            return contains;
        }
    }

    @Override
    public void run() {
        if (integer==null) integer = new GoodListHelper<>();
        integer.putIfAbsent(1);
        System.out.println(list.toString());
        System.out.println("-----------------");
    }

    public static void main(String[] args) {
        GoodListHelper<Integer> integerBadListHelpers = new GoodListHelper<>();


        Thread thread = new Thread(integerBadListHelpers);
        thread.start();

        Thread thread1 = new Thread(integerBadListHelpers);
        thread1.start();

        Thread thread2 = new Thread(integerBadListHelpers);
        thread2.start();
    }
}
