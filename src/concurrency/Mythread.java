package concurrency;

/**
 * @author lijian
 * @description 继承Thread类
 * @date 2019/12/25
 */
public class Mythread extends Thread {
    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        mythread.start();
    }

    @Override
    public void run() {
        System.out.println(" I'm runing");
        //使用this直接获取当前线程
        System.out.println(this.getName());
    }
}