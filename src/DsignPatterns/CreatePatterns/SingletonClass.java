package DsignPatterns.CreatePatterns;

/**
 * @author lijian
 * @description 单例模式
 * @date 2020/4/16
 */
public class SingletonClass {


    private static volatile SingletonClass instance=null;    //保证 instance 在所有线程中同步 懒汉模式
    private SingletonClass(){}    //private 避免类在外部被实例化
    public static synchronized SingletonClass getInstance()
    {
        //getInstance 方法前加同步
        if(instance==null)
        {
            instance=new SingletonClass();
        }
        return instance;
    }

}
    //饿汉模式
 class HungrySingleton
{
    //初始化就创建
    private static final HungrySingleton instance=new HungrySingleton();
    private HungrySingleton(){}
    public static HungrySingleton getInstance()
    {
        return instance;
    }
}