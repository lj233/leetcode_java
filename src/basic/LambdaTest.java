package basic;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author lijian
 * @description
 * @date 2020/6/29
 *
 * @FunctionalInterface 注解 + 唯一抽象方法
 */
public class LambdaTest {
    public static void main(String[] args) {
        // Consumer 一个入参，无返回值
        Consumer<String> consumer = s-> System.out.println(s);
        consumer.accept("Java3y");

        // Supplier 无入参，有返回值
        Supplier<String> supplier = () -> "Java4y";
        String s = supplier.get();
        System.out.println(s);

        new Thread(() -> System.out.println("run"));
    }
}
