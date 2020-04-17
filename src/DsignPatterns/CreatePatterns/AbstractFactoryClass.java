package DsignPatterns.CreatePatterns;

/**
 * @author lijian
 * @description 抽象工厂方法
 * @date 2020/4/17
 *
 * 抽象工厂（AbstractFactory）模式的定义：是一种为访问类提供一个创建一组相关或相互依赖对象的接口，且访问类无须指定所要产品的具体类就能得到同族的不同等级的产品的模式结构。
 *
 * 抽象工厂模式是工厂方法模式的升级版本，工厂方法模式只生产一个等级的产品，而抽象工厂模式可生产多个等级的产品。
 *
 * 使用抽象工厂模式一般要满足以下条件。
 * 系统中有多个产品族，每个具体工厂创建同一族但属于不同等级结构的产品。
 * 系统一次只可能消费其中某一族产品，即同族的产品一起使用。
 *
 * 抽象工厂模式除了具有工厂方法模式的优点外，其他主要优点如下。
 * 可以在类的内部对产品族中相关联的多等级产品共同管理，而不必专门引入多个新的类来进行管理。
 * 当增加一个新的产品族时不需要修改原代码，满足开闭原则。
 *
 * 其缺点是：当产品族中需要增加一个新的产品时，所有的工厂类都需要进行修改
 */
public class AbstractFactoryClass {
    public static void main(String[] args)
    {
        try
        {
            AbstractProduct a;
            AbstractAbstractFactory af;
            af=new AbstractConcreteFactory1();
            a=af.newProduct();
            a.show();
            a.show2();
            System.out.println("#############################################");
            af=new AbstractConcreteFactory2();
            a=af.newProduct2();
            a.show();
            a.show2();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

//抽象产品：提供了产品的接口
interface AbstractProduct
{
    public void show();
    public void show2();
}
//具体产品族1：实现抽象产品中的抽象方法
class AbstractConcreteProduct1 implements AbstractProduct
{
    public void show()
    {
        System.out.println("具体产品族1产品1显示...");
    }
    public void show2()
    {
        System.out.println("具体产品族1产品2显示...");
    }
}
//具体产品2：实现抽象产品中的抽象方法
class AbstractConcreteProduct2 implements AbstractProduct
{
    public void show()
    {
        System.out.println("具体产品族2产品1显示...");
    }
    public void show2()
    {
        System.out.println("具体产品族2产品2显示...");
    }
}
//抽象工厂：提供了厂品的生成方法
interface AbstractAbstractFactory
{
    public AbstractProduct newProduct();
    public AbstractProduct newProduct2();
}
//具体工厂1：实现了厂品的生成方法
class AbstractConcreteFactory1 implements AbstractAbstractFactory
{
    public AbstractProduct newProduct()
    {
        System.out.println("具体工厂1生成-->具体产品11...");
        return new AbstractConcreteProduct1();
    }
    public AbstractProduct newProduct2()
    {
        System.out.println("具体工厂1生成-->具体产品12...");
        return new AbstractConcreteProduct1();
    }
}
//具体工厂2：实现了厂品的生成方法
class AbstractConcreteFactory2 implements AbstractAbstractFactory
{
    public AbstractProduct newProduct()
    {
        System.out.println("具体工厂2生成-->具体产品21...");
        return new AbstractConcreteProduct1();
    }
    public AbstractProduct newProduct2()
    {
        System.out.println("具体工厂2生成-->具体产品22...");
        return new AbstractConcreteProduct2();
    }
}