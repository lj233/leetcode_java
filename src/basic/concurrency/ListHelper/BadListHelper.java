package basic.concurrency.ListHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author lijian
 * @description 客户端加速机制，若没有则添加
 * @date 2020/7/1
 */
public class BadListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    // 在错误的锁上进行了同步
    public synchronized boolean putIfAbsent(E x) {

        boolean absent = !list.contains(x);	//①
        //锁住 Helper对象两秒  并且获取到了错误的结果
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (absent) {
            list.add(x);
        }
        System.out.println(list.toString());
        return absent;

    }

    public static void main(String[] args) {
        BadListHelper<String> helper = new BadListHelper<String>();
        new Thread(new Runnable() {

            @Override
            public void run() {
                helper.putIfAbsent("a");
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                // 只是锁住了Helper对象，仍可以对 list进行操作
                helper.list.add("a");    //②
                System.out.println("仍可以对 list 进行操作");
            }
        }).start();




    }
}
