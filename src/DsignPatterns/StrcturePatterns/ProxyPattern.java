package DsignPatterns.StrcturePatterns;

import java.lang.reflect.Proxy;

/**
 * @Description:  代理模式  我自己是明星 找经纪人帮我处理事情
 * 一个接口  两个实现类
 * @Author: lij
 */
public interface ProxyPattern {
    void show();
}
class ProxyAchieve implements ProxyPattern{
    show show;

    public ProxyAchieve(show show) {
        this.show = show;
    }

    public void doSomeThing(){
        System.out.println("团结队友");
    }

    @Override
    public void show() {
        show.show();

        doSomeThing();

    }
}

class show implements ProxyPattern{

    @Override
    public void show() {
        System.out.println("我自己疯狂输出");
    }
}
