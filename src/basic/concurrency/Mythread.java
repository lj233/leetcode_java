package basic.concurrency;

import basic.concurrency.utils.SemaphoreService;

/**
 * @author lijian
 * @description 继承Thread类
 * @date 2019/12/25
 *
 * Semaphore 是 synchronized 的加强版，作用是控制线程的并发数量。
 */
public class Mythread extends Thread {

    private SemaphoreService service;

    public static void main(String[] args) throws InterruptedException{
//        new Mythread().testSemphore();
        new Mythread().testInterrupt();
    }

    public Mythread() {

    }

    public Mythread(Runnable target) {
        super(target);
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

    public void testSemphore(){
        SemaphoreService service = new SemaphoreService();
        for (int i = 0; i < 10; i++) {
            Mythread t = new Mythread("thread" + (i + 1), service);
            t.start();
        }
    }

    public void testInterrupt() throws InterruptedException{
        Runnable runnable = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(2000);
        // 在阻塞是调用 会抛异常  具体可以看源码
        t.interrupt();

    }
}