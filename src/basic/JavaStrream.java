package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

/**
 * @author lijian  中间操作返回Stream流对象，最终操作返回非流对象
 * @description java8新特性，stream流
 * @date 2020/4/3
 */
public class JavaStrream {
    public static void main(String[] args) {



    }

    public void stream(){
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(2);
        integers.add(4);
        integers.add(3);
        integers.add(5);
        integers.stream()
                .filter(b -> b == 3)
                .sorted();
//                .mapToInt(Widget::getWeight)
//                .sum();
    }

    //lambda表达式实例
    public void lambdaTest(){
        new Thread(()-> System.out.println("lambda写法")).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("内部类写法");
            }
        }).start();

        IntBinaryOperator intBinaryOperator = (int a, int b) -> a + b;

    }
}
