package concurrency;

/**
 * @author lijian
 * @description 实现runnable接口
 * @date 2019/12/25
 */
public class MyRunnable implements Runnable {

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println("In Java8, Lambda expression");
            System.out.println("111");
        }).start();
        Thread.sleep(2000);
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }


    @Override
    public void run() {
        System.out.println("I'm in Runnable Running");
    }
}
