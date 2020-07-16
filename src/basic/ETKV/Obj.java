package basic.ETKV;

/**
 * @author lijian
 * @description 泛型 测试类   使用Object表示任意的类，强转不方便，不安全
 *
 * 泛型是提供给javac编译器使用的，它用于限定集合的输入类型，让编译器在源代码级别上，即挡住向集合中插入非法数据。
 * 但编译器编译完带有泛形的java程序后，生成的class文件中将不再带有泛形信息，以此使程序运行效率不受到影响，这个过程称之为“擦除”
 * @date 2020/6/23
 */
public class Obj<T> {
    // 定义泛型类
    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    //定义泛型方法..
    public <T> T show(T t) {
        System.out.println(t);
        return t;
    }

    public static void main(String[] args) {
        //创建对象
        Obj tool = new Obj();

        //调用方法,传入的参数是什么类型,返回值就是什么类型
        tool.show("hello");
        tool.show(12);
        tool.show(12.5);

    }


}
