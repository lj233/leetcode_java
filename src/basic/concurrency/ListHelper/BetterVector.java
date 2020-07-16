package basic.concurrency.ListHelper;

import java.util.Vector;

/**
 * @author lijian 增加若没有则添加的方法
 * @description 扩展vector类
 * @date 2020/7/2
 */
public class BetterVector<E> extends Vector<E> {
    // Vector 已经实现了同步策略，即方法上有 Syn同步
    public synchronized boolean addIfAbsent(E x){
        boolean absent = !contains(x);
        if (absent)
            add(x);
        return absent;
    }
}
