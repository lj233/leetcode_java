package basic.ETKV;

import java.util.Comparator;
import java.util.List;

/**
 * @author lijian
 * @description 子类不明确泛型类的类型参数变量
 * @date 2020/6/23
 */
public class InterImpl2<T> implements Inter<T> {

    @Override
    public void show(T t) {

        System.out.println(t);
    }
    //类型通配符  ？ 代替了 Object  只能调对象与类型无关的方法，不能调用对象与类型有关的方法。
    public void test(List<?> list){


        for(int i=0;i<list.size();i++){

            System.out.println(list.get(i));

        }
    }

    /**
     * @Description:
     * 原则：
     *
     * 如果参数之间的类型有依赖关系，或者返回值是与参数之间有依赖关系的。那么就使用泛型方法
     *
     * 如果没有依赖关系的，就使用通配符，通配符会灵活一些
     * @Author: lij
     * @param list
     * @return: void
     */

    // 设定 通配符的上限 List集合装载的元素只能是Number的子类或自身
    public static void test2(List<? extends Number> list) {

    }

    // 设定通配符的下限 只能是numer本身或者number的父类
    public static void test3(Comparator<? super Number> list) {

    }
}
