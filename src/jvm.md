# jvm

## 马士兵视频

### 命令

java -X 输出 不标准的参数  

java -XX 不稳定的参数  根据各版本不同

java -XX:PrintFlagsFinal 查看最后的 、调优后的参数

-Xms 和 -Xmx 最好大小一样 避免不必要的扩容和收缩

-XX:+PrintGC  打印日志


jps  列出 java进程


jstack (| more) 打印线程栈 jps+jstack  prio 优先级

jinfo pid 查看进程的虚拟机信息


jstat -gc pid 输出gc的情况

jmap 查看有多少对象产生  产生堆转储文件 会暂停运行的用户线程 线上不能这么作

arthas 阿里开源的 代替 jvisualvm 需要开端口才能远程 不安全