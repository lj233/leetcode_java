package basic.concurrency.utils;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author lijian
 * @description 防止cas  ABA问题
 *  * 使用CAS有个缺点就是ABA的问题，什么是ABA问题呢？首先我用文字描述一下：
 *  *
 *  * 现在我有一个变量count=10，现在有三个线程，分别为A、B、C
 *  *
 *  * 线程A和线程C同时读到count变量，所以线程A和线程C的内存值和预期值都为10
 *  *
 *  * 此时线程A使用CAS将count值修改成100
 *  *
 *  * 修改完后，就在这时，线程B进来了，读取得到count的值为100(内存值和预期值都是100)，将count值修改成10
 *  *
 *  * 线程C拿到执行权，发现内存值是10，预期值也是10，将count值修改成11
 * @date 2020/6/29
 */
public class AtomicStampedReferenceTest {

    public static void main(String[] args) {
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("0",0);
        atomicStampedReference.compareAndSet("1","2",0,1);
        System.out.println(atomicStampedReference.getReference());
        boolean a = Objects.equals("a", "a");
        System.out.println(a);
    }
}
