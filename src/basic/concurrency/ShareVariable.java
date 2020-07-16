package basic.concurrency;

/**
 * https://www.cnblogs.com/xudong-bupt/archive/2013/05/22/3087864.html
 * 两个线程都持有一个Num实例，利用可见性保持一致
 * 可见性
 * 可见性指多个线程操作一个共享变量时，其中一个线程对变量进行修改后，其他线程可以立即看到修改的结果。
 * 实现可见性：synchronized 或者 Lock：保证同一个时刻只有一个线程获取锁执行代码，锁释放之前把最新的值刷新到主内存，
 */
public class ShareVariable {



    public static void main(String[] args) throws InterruptedException {
        Num i = new Num(0);    //新建对象，准备传递给线程
        new OwnThread(i).start();    //新建线程，并启动
        new OwnThread(i).start();    //新建线程，并启动
        System.out.println("主线程中i的值变为了：" + i.i);    //获取目前对象i的数值
        //----------------------------------------------
        //验证volatile关键字的作用
        Thread.sleep(2000);
        Variable variable = new Variable();
        Thread thread = new Thread(variable);
        thread.start();
        Thread thread1 = new Thread(variable);
        thread1.start();

    }
}

class OwnThread extends Thread {
    Num id;    //申明对象，默认null，就是没有指向任何实体
    int sno;    //申明int变量。因为系统默认初始化为0，所以应该是定义一个int变量


    OwnThread(Num id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
//            synchronized(OwnThread.class) 锁住当前对象
            synchronized (this) {
                sno = id.i;    //保存id.i的数值，到线程私有变量sno，因为两个线程共享变量Num是可见的
                id.i++;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }

            System.out.println(this.getName() + "," + sno);
        }
    }
}

class Variable implements Runnable {
    private volatile int variable = 10;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this);
            synchronized (this) {
                variable++;
                System.out.println(Thread.currentThread().getName() + "  " + variable);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Num    //定义一个类
{
    int i;

    Num(int i) {
        this.i = i;
    }
}
