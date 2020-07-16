package basic.concurrency;


import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author lijian
 * @description 实现runnable接口
 * 1、Thread中有一个map，就是ThreadLocalMap
 *
 * 2、ThreadLocalMap的key是ThreadLocal，值是我们自己设定的
 * 3、这个变量是针对一个线程内所有操作共享的，所以设置为静态变量，所有此类实例共享此静态变量，
 * @date 2019/12/25
 */
public class MyRunnable implements Runnable {

    public static void main(String[] args) throws Exception {
        //正常设置为static
        ThreadLocal<String> df = new ThreadLocal<>();
        Random random = new Random();
        IntStream.range(0,5).forEach(value ->  new Thread(() -> {
            df.set(value +" "+random.nextInt(10));
            System.out.println("线程和local值分别是"+df.get());
            System.out.println(Thread.currentThread().getName());
            //防止线程复用造成业务逻辑和内存泄漏
//            df.remove();
        }).start());
        System.out.println(Thread.currentThread().getName());



        Thread.sleep(2000);
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }


    @Override
    public void run() {
        System.out.println("I'm in Runnable Running");
        //获取当前线程
        System.out.println(Thread.currentThread().getName());
    }
}
