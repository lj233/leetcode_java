package basic.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author lijian
 * @description
 * @date 2020/6/24
 */
public class BlockClient {

    public static void main(String[] args) throws IOException {

        // 1. 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));

        // 2. 发送一张图片给服务端吧
        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\lijian\\Pictures\\孙嘉新文件\\1.jpg"), StandardOpenOption.READ);

        // 3.要使用NIO，有了Channel，就必然要有Buffer，Buffer是与数据打交道的呢
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 4.读取本地文件(图片)，发送到服务器
        while (fileChannel.read(buffer) != -1) {

            // 在读之前都要切换成读模式
            buffer.flip();

            socketChannel.write(buffer);

            // 读完切换成写模式，能让管道继续读取文件的数据
            buffer.clear();
        }
        // 需要 告诉服务端 已经洗完了  如果没有这句话，不会关闭流，服务端不知道还有没有数据要发送
        socketChannel.shutdownOutput();

        int len = 0;
        // 一直轮询获取数据
        while ((len = socketChannel.read(buffer)) != -1){
            buffer.flip();
            System.out.println(new String(buffer.array(),0,len));

            buffer.clear();
        }


        // 5. 关闭流 服务器端关闭后他才关闭
        fileChannel.close();
        socketChannel.close();
    }
}
