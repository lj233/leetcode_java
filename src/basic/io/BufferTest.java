package basic.io;

import java.nio.ByteBuffer;

/**
 * @author lijian
 * @description 缓冲区参数介绍  Buffer 的参数
 * @date 2020/6/24
 */
public class BufferTest {

    public static void main(String[] args) {

        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 看一下初始时4个核心变量的值
        System.out.println("初始时-->limit 界限--->"+byteBuffer.limit());
        System.out.println("初始时-->position 位置--->"+byteBuffer.position());
        System.out.println("初始时-->capacity 容量--->"+byteBuffer.capacity());
        System.out.println("初始时-->mark  标记--->" + byteBuffer.mark());

        System.out.println("--------------------------------------");

        // 添加一些数据到缓冲区中
        String s = "Java Nio";
        byteBuffer.put(s.getBytes());

        // 看一下put后时4个核心变量的值
        System.out.println("put完之后-->limit--->"+byteBuffer.limit());
        System.out.println("put完之后-->position--->"+byteBuffer.position());
        System.out.println("put完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("put完之后-->mark--->" + byteBuffer.mark());

        System.out.println("--------------------------------------");
        System.out.println("byteBuffer.flip() 切换成读模式 = " + byteBuffer.flip());
        System.out.println("flip完之后-->limit--->"+byteBuffer.limit());
        System.out.println("flip完之后-->position--->"+byteBuffer.position());
        System.out.println("flip完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("flip完之后-->mark--->" + byteBuffer.mark());


        System.out.println("--------------------------------------");
        byte[] bytes = new byte[byteBuffer.limit()];
        // 将读取的数据封装到字节数组中
        byteBuffer.get(bytes);
        System.out.println("byteBuffer.get()  = " + new String(bytes,0,bytes.length));
        System.out.println("get完之后-->limit--->"+byteBuffer.limit());
        System.out.println("get完之后-->position--->"+byteBuffer.position());
        System.out.println("get完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("get完之后-->mark--->" + byteBuffer.mark());


        System.out.println("--------------------------------------");
        byteBuffer.clear();
        System.out.println("clear完之后-->limit--->"+byteBuffer.limit());
        System.out.println("clear完之后-->position--->"+byteBuffer.position());
        System.out.println("clear完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("clear完之后-->mark--->" + byteBuffer.mark());



    }
}
