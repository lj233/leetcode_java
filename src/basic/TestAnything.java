package basic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author lijian
 * @description 测试验证任何遇到的问题
 * @date 2020/4/10
 */
public class TestAnything {
    public static void main(String[] args) {
        TestAnything testAnything = new TestAnything();
        testAnything.testFloat();
    }





    public void testFloat(){
        float a = 0.1f;
        float b = 0.2f;
        float d = 0.3f;
        double c = 0.3;
        if (c==(a+b)){
            System.out.println("double true");
        }
        else System.out.println("double false");


        if (d==(a+b)){
            System.out.println("float  true");
        }
        else System.out.println(" float false");


        BigDecimal bigDecimal = new BigDecimal(0.1);
        BigDecimal bigDecimal2 = new BigDecimal(0.2);
        BigDecimal bigDecimal3 = bigDecimal.add(bigDecimal2);
        BigDecimal bigDecimal4 = new BigDecimal(0.3);
        //
        if (bigDecimal4.compareTo(bigDecimal3)==0){
            System.out.println("bigDecimal true");
        } else System.out.println("bigDecimal false");

        if (bigDecimal.compareTo(bigDecimal2)>-1){
            System.out.println(" 0.1>=0.2");
        }
        if (bigDecimal.compareTo(bigDecimal2)<1){
            System.out.println(" 0.1<=0.2");
        }

        System.out.println(bigDecimal.compareTo(bigDecimal2)+"11");
    }
}
