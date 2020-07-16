package basic.ETKV;

/**
 * @author lijian
 * @description 子类明确泛型类的类型参数变量
 * @date 2020/6/23
 *
 * 比如说public<U> Optional<U> map(Function<? super T, ? extends U> mapper)这个声明，你看懂了吗？
 *
 * // 接口
 * @FunctionalInterface
 * public interface Function<T, R> {
 *     R apply(T t);
 * }
 * 在泛型的上限和下限中有一个原则：PECS(Producer Extends Consumer Super)
 *
 * 带有子类限定的可以从泛型读取【也就是--->(? extend T)】-------->Producer Extends
 *
 * 带有超类限定的可以从泛型写入【也就是--->(? super T)】-------->Consumer Super
 *
 * 解析：传入的参数是泛型 T 或者其父类，返回值是U或其子类。
 */
public class InterImpl implements Inter<String>{

    @Override
    public void show(String s) {
        System.out.println(s);
    }
}
