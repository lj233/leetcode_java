package basic.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author lijian
 * @description
 * @date 2020/6/24
 */
public class BlockServer {

    public static void main(String[] args) throws IOException {

        // 1.获取通道
        ServerSocketChannel server = ServerSocketChannel.open();

        // 2.得到文件通道，将客户端传递过来的图片写到本地项目下(写模式、没有则创建)
        FileChannel outChannel = FileChannel.open(Paths.get("2.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // 3. 绑定链接
        server.bind(new InetSocketAddress(6666));

        // 4. 获取客户端的连接(阻塞的)
        System.out.println("阻塞监听");
        SocketChannel client = server.accept();

        // 5. 要使用NIO，有了Channel，就必然要有Buffer，Buffer是与数据打交道的呢
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 6.将客户端传递过来的图片保存在本地中
        while (client.read(buffer) != -1) {

            // 在读之前都要切换成读模式
            buffer.flip();

            outChannel.write(buffer);

            // 读完切换成写模式，能让管道继续读取文件的数据
            buffer.clear();

        }
        //
        buffer.put("image is success".getBytes());
        buffer.flip();
        client.write(buffer);
        buffer.clear();

        // 7.关闭通道
        outChannel.close();
        client.close();
        server.close();
    }
}
