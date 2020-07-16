package basic.concurrency.ListHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.UnaryOperator;

/**
 * @author lijian
 * @description
 * @date 2020/7/1
 */
public class ImprovedList<E> implements List<E> {

    private final List<E> list;

    public static void main(String[] args) {
        ImprovedList<String> improvedList = new ImprovedList<>(new ArrayList<>());
        new Thread(() -> {
            System.out.println("线程锁执行 ");
            improvedList.putIfAbsent("a");
        }).start();

        new Thread(() -> {
            System.out.println("成员变量方法执行");
            improvedList.putIfAbsent("b");
            // 因为list被锁住 improvedList.list.toString()也稍后执行
        }).start();

        new Thread(() -> {
            System.out.println("需要等上面的锁结束后 才能执行");
            improvedList.list.add("c");
            System.out.println(improvedList.list.toString());
        }).start();
    }

    // 构造函数指定初始化
    public ImprovedList(List<E> list) {
        this.list = list;
    }

    // 锁住了对象，自然直接锁住了 list（属性） ×
    // 通过将list对象的操作委托给底层的List实例 即内置锁的特性
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (absent) {
            list.add(x);
        }
        System.out.println("improvedList = " + list.toString());
        return absent;

    }

    public synchronized boolean add(E arg0) {
        return list.add(arg0);
    }
    // ... 按照类似的方式委托list其他方法


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }
}
