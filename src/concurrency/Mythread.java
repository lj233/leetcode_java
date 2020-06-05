package concurrency;

/**
 * @author lijian
 * @description 继承Thread类
 * @date 2019/12/25
 *
 * Semaphore 是 synchronized 的加强版，作用是控制线程的并发数量。
 */
public class Mythread extends Thread {

    private SemaphoreService service;

    public static void main(String[] args) {
        SemaphoreService service = new SemaphoreService();
        for (int i = 0; i < 10; i++) {
            Mythread t = new Mythread("thread" + (i + 1), service);
            t.start();// 这里使用 t.run() 也可以运行，但是不是并发执行了
        }
    }
    public Mythread(String name, SemaphoreService service) {
        super();
        this.setName(name);
        this.service = service;
    }
    @Override
    public void run() {
        System.out.println(" I'm runing");
        //使用this直接获取当前线程
        this.service.doSomething();
        System.out.println(this.getName());
    }
}