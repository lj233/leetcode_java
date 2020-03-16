package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author lijian
 * @description 有返回值的线程
 * @date 2019/12/26
 */
public class MyCallable implements Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        FutureTask<String> objectFuture = new FutureTask<String>(myCallable);
        new Thread(objectFuture).start();
        System.out.println(objectFuture.get());
    }

    @Override
    public Object call() throws Exception {
        int i = 3;
        while (i > 0) {
            System.out.println(" Callable while");
            i--;
        }
        return "callable return";
    }
}
