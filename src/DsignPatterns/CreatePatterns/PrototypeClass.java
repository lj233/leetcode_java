package DsignPatterns.CreatePatterns;

/**
 * @author lijian
 * @description 原型模式
 * @date 2020/4/17
 *
 *  实现clone（） 只是浅克隆，
 *  深克隆需要类的引用对象都实现cloneable接口（递归），或者使用对象流
 *
 * 由于 Java 提供了对象的 clone() 方法，所以用 Java 实现原型模式很简单。
 * 模式的结构
 * 原型模式包含以下主要角色。
 * 抽象原型类：规定了具体原型对象必须实现的接口。
 * 具体原型类：实现抽象原型类的 clone() 方法，它是可被复制的对象。
 * 访问类：使用具体原型类中的 clone() 方法来复制新的对象。
 */

//测试类
public class PrototypeClass {
    public static void main(String[] args)throws CloneNotSupportedException
    {
        Realizetype obj1=new Realizetype();
        Realizetype obj2=(Realizetype)obj1.clone();
        System.out.println("obj1==obj2?"+(obj1==obj2));
    }
}


interface Shape extends Cloneable
{
    public Object clone();    //拷贝
    public void countArea();    //计算面积
}

//具体原型类
class Realizetype implements Cloneable
{
    Realizetype()
    {
        System.out.println("具体原型创建成功！");
    }
    public Object clone() throws CloneNotSupportedException
    {
        System.out.println("具体原型复制成功！");
        return (Realizetype)super.clone();
    }
}