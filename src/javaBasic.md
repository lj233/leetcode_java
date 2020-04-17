# 异常类

Throwable 分为 Error(内存异常)  和 Exception（RuntimeException 和 CheckedException)

## throw 和 throws

位置不同

throws 用在方法上，后面可跟多个类， 
throw 在方法内，后面跟的是 异常对象

功能不同

throws标识可能会抛出异常
throw 执行时会直接抛出异常，并不会执行后面的语句，并由上级处理异常

相同点：两者都是由上级处理异常，都是消极的


# 反射机制复习

动态语言：可以在运行期间改变其结构，如python、js，java属于半动态。

反射概念：在运行状态中，能知道任何一个类的属性和方法。

应用场景：如果编译时根本无法预知该对象和类属于哪个类，只能依靠运行时发现真实信息，则需要
          用到反射。
          
          
      
  ## 反射 API 
  用来生成 JVM 中的类、接口或则对象的信息。
  1. Class 类：反射的核心类，可以获取类的属性，方法等信息。
  2. Field 类：Java.lang.reflec 包中的类，表示类的成员变量，可以用来获取和设置类之中的属性
  值。
  3. Method 类： Java.lang.reflec 包中的类，表示类的方法，它可以用来获取类中的方法信息或
  者执行方法。
  4. Constructor 类： Java.lang.reflec 包中的类，表示类的构造方法。
  
 ## 获取 Class 对象的 3 种方法
  调用某个对象的 getClass()方法
  Person p=new Person();
  Class clazz=p.getClass();
  调用某个类的 class 属性来获取该类对应的 Class 对象
  Class clazz=Person.class;
  使用 Class 类中的 forName()静态方法(最安全/性能最好)
  Class clazz=Class.forName("类的全路径"); (最常用)
  
  ```
  //获取 Person 类的所有方法信息
     Method[] method=clazz.getDeclaredMethods();
     for(Method m:method){
     System.out.println(m.toString());
     }
     //获取 Person 类的所有成员属性信息
     Field[] field=clazz.getDeclaredFields();
     for(Field f:field){
     System.out.println(f.toString());
     }
     //获取 Person 类的所有构造方法信息
     Constructor[] constructor=clazz.getDeclaredConstructors();
     for(Constructor c:constructor){
     System.out.println(c.toString());
     }
  ```

## 创建对象的两种方法

    1.Class 对象的 newInstance()
    2.调用 Constructor 对象的 newInstance()
    
    # java注解
    
    Annotation（注解）是 Java 提供的一种对元程序中元素关联信息和元数据（metadata）的途径
    和方法。Annatation(注解)是一个接口，程序可以通过反射来获取指定程序中元素的 Annotation
    对象，然后通过该 Annotation 对象来获取注解中的元数据信息。
    
    ## 4种标准元注解
        @Target 修饰的对象范围
        @Target说明了Annotation所修饰的对象范围： Annotation可被用于 packages、types（类、
        接口、枚举、Annotation 类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数
        和本地变量（如循环变量、catch 参数）。在 Annotation 类型的声明中使用了 target 可更加明晰
        其修饰的目标
        @Retention 定义 被保留的时间长短
        Retention 定义了该 Annotation 被保留的时间长短：表示需要在什么级别保存注解信息，用于描
        述注解的生命周期（即：被描述的注解在什么范围内有效），取值（RetentionPoicy）由：
         SOURCE:在源文件中有效（即源文件保留）
         CLASS:在 class 文件中有效（即 class 保留）
         RUNTIME:在运行时有效（即运行时保留）
        @Documented ᧿述-javadoc
        @ Documented 用于描述其它类型的 annotation 应该被作为被标注的程序成员的公共 API，因
        此可以被例如 javadoc 此类的工具文档化。
        @Inherited 阐述了某个被标注的类型是被继承的
        @Inherited 元注解是一个标记注解，@Inherited 阐述了某个被标注的类型是被继承的。如果一
        个使用了@Inherited 修饰的 annotation 类型被用于一个 class，则这个 annotation 将被用于该
        class 的子类。

# 内部类

Java 类中不仅可以定义变量和方法，还可以定义类，这样定义在类内部的类就被称为内部类。根
据定义的方式不同，内部类分为静态内部类，成员内部类，局部内部类，匿名内部类四种。

## 静态内部类
定义在类内部的静态类，就是静态内部类。
  1. 静态内部类可以访问外部类所有的静态变量和方法，即使是 private 的也一样。
  2. 静态内部类和一般类一致，可以定义静态变量、方法，构造方法等。
  3. 其它类使用静态内部类需要使用“外部类.静态内部类”方式，如下所示：Out.Inner inner =
  new Out.Inner();inner.print();
  4. Java集合类HashMap内部就有一个静态内部类Entry。Entry是HashMap存放元素的抽象，
  HashMap 内部维护 Entry 数组用了存放元素，但是 Entry 对使用者是透明的。像这种和外部
  类关系密切的，且不依赖外部类实例的，都可以使用静态内部类。

# 成员内部类

  定义在类内部的非静态类，就是成员内部类。成员内部类不能定义静态方法和变量（final 修饰的
  除外）。这是因为成员内部类是非静态的，类初始化的时候先初始化静态成员，如果允许成员内
  部类定义静态变量，那么成员内部类的静态变量初始化顺序是有歧义的。


# 局部内部类
定义在方法中的类，就是局部类。如果一个类只在某个方法中使用，则可以考虑使用局部类。

# 匿名内部类

匿名内部类我们必须要继承一个父类或者实现一个接口，当然也仅能只继承一个父类或者实现一
个接口。同时它也是没有 class 关键字，这是因为匿名内部类是直接使用 new 来生成一个对象的引
用。
````
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("内部类写法");
            }
        }).start();
````


# java泛型

  泛型提供了编译时类型安全检测机制，该机制允许程序员在编译时检测到非法的类型。泛型的本
  质是参数化类型，也就是说所操作的数据类型被指定为一个参数。比如我们要写一个排序方法，
  能够对整型数组、字符串数组甚至其他任何类型的数组进行排序，我们就可以使用 Java 泛型。

## 泛型方法（<E>）
你可以写一个泛型方法，该方法在调用时可以接收不同类型的参数。根据传递给泛型方法的参数
类型，编译器适当地处理每一个方法调用。

1. <? extends T>表示该通配符所代表的类型是 T 类型的子类。
2. <? super T>表示该通配符所代表的类型是 T 类型的父类。


## 泛型类<T>
泛型类的声明和非泛型类的声明类似，除了在类名后面添加了类型参数声明部分。和泛型方法一
样，泛型类的类型参数声明部分也包含一个或多个类型参数，参数间用逗号隔开。一个泛型参数，
也被称为一个类型变量，是用于指定一个泛型类型名称的标识符。因为他们接受一个或多个参数，
这些类被称为参数化的类或参数化的类型。

````
public class Box<T> {
 private T t;
 public void add(T t) {
 this.t = t;
 }
 public T get() {
 return t;
 }
````

## 类型通配符?
类型通配符一般是使用 ? 代替具体的类型参数。例如 List<?> 在逻辑上是
List<String>,List<Integer> 等所有 List<具体类型实参>的父类。


## 类型擦除
Java 中的泛型基本上都是在编译器这个层次来实现的。在生成的 Java 字节代码中是不包含泛
型中的类型信息的。使用泛型的时候加上的类型参数，会被编译器在编译的时候去掉。这个
过程就称为类型擦除。如在代码中定义的 List<Object>和 List<String>等类型，在编译之后
都会变成 List。JVM 看到的只是 List，而由泛型附加的类型信息对 JVM 来说是不可见的。
类型擦除的基本过程也比较简单，首先是找到用来替换类型参数的具体类。这个具体类一般
是 Object。如果指定了类型参数的上界的话，则使用这个上界。把代码中的类型参数都替换
成具体的类。


# java 序列化

  保存(持久化)对象及其状态到内存或者磁盘
  Java 平台允许我们在内存中创建可复用的 Java 对象，但一般情况下，只有当 JVM 处于运行时，
  这些对象才可能存在，即，这些对象的生命周期不会比 JVM 的生命周期更长。但在现实应用中，
  就可能要求在JVM停止运行之后能够保存(持久化)指定的对象，并在将来重新读取被保存的对象。
  Java 对象序列化就能够帮助我们实现该功能。
  序列化对象以字节数组保持-静态成员不保存
  使用 Java 对象序列化，在保存对象时，会把其状态保存为一组字节，在未来，再将这些字节组装
  成对象。必须注意地是，对象序列化保存的是对象的”状态”，即它的成员变量。由此可知，对
  象序列化不会关注类中的静态变量。
  
  序列化用户远程对象传输
  除了在持久化对象时会用到对象序列化之外，当使用 RMI(远程方法调用)，或在网络中传递对象时，
  都会用到对象序列化。Java序列化API为处理对象序列化提供了一个标准机制，该API简单易用。
  Serializable 实现序列化
  在 Java 中，只要一个类实现了 java.io.Serializable 接口，那么它就可以被序列化。
  ObjectOutputStream 和 ObjectInputStream 对对象进行序列化及反序列化
  通过 ObjectOutputStream 和 ObjectInputStream 对对象进行序列化及反序列化。
  writeObject 和 readObject 自定义序列化策略
  在类中增加 writeObject 和 readObject 方法可以实现自定义序列化策略。
  
  序列化 ID
  虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个
  类的序列化 ID 是否一致（就是 private static final long serialVersionUID）
  序列化并不保存静态变量
  序列化子父类说明
  要想将父类对象也序列化，就需要让父类也实现 Serializable 接口。
  Transient 关键字阻止该变量被序列化到文件中
  1. 在变量声明前加上 Transient 关键字，可以阻止该变量被序列化到文件中，在被反序列
  化后，transient 变量的值被设为初始值，如 int 型的是 0，对象型的是 null。
  2. 服务器端给客户端发送序列化对象数据，对象中有一些数据是敏感的，比如密码字符串
  等，希望对该密码字段在序列化时，进行加密，而客户端如果拥有解密的密钥，只有在
  客户端进行反序列化时，才可以对密码进行读取，这样可以一定程度保证序列化对象的
  数据安全。
  
  # java复制
  ````
  package abc;  
    
  class Address implements Cloneable {  
      private String add;  
    
      public String getAdd() {  
          return add;  
      }  
    
      public void setAdd(String add) {  
          this.add = add;  
      }  
        
      @Override  
      public Object clone() {  
          Address addr = null;  
          try{  
              addr = (Address)super.clone();  
          }catch(CloneNotSupportedException e) {  
              e.printStackTrace();  
          }  
          return addr;  
      }  
  }  
    
  class Student implements Cloneable{  
      private int number;  
    
      private Address addr;  
      //引用对象不重写方法，只是浅克隆、引用的地址会是一个，并不会开辟新的空间
      public Address getAddr() {  
          return addr;  
      }  
    
      public void setAddr(Address addr) {  
          this.addr = addr;  
      }  
    
      public int getNumber() {  
          return number;  
      }  
    
      public void setNumber(int number) {  
          this.number = number;  
      }  
        
      @Override  
      public Object clone() {  
          Student stu = null;  
          try{  
              stu = (Student)super.clone();   //浅复制  
          }catch(CloneNotSupportedException e) {  
              e.printStackTrace();  
          }  
          stu.addr = (Address)addr.clone();   //深度复制  
          return stu;  
      }  
  }  
  public class Test {  
        
      public static void main(String args[]) {  
            
          Address addr = new Address();  
          addr.setAdd("杭州市");  
          Student stu1 = new Student();  
          stu1.setNumber(123);  
          stu1.setAddr(addr);  
            
          Student stu2 = (Student)stu1.clone();  
            
          System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());  
          System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());  
            
          addr.setAdd("西湖区");  
            
          System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());  
          System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());  
      }  
  }
  ````
   *  实现clone（） 只是浅克隆，
   *  深克隆需要类的引用对象都实现cloneable接口（递归），或者使用对象流
  
 ```
//  将对象写到流里    
	ByteArrayOutputStream byteOut=new ByteArrayOutputStream();    
	ObjectOutputStream objOut=new ObjectOutputStream(byteOut);    
	objOut.writeObject(father);
	//从流里读出来    
	ByteArrayInputStream byteIn=new ByteArrayInputStream(byteOut.toByteArray());    
	ObjectInputStream objInput=new ObjectInputStream(byteIn);
    fatherCopy = (Son) objInput.readObject();
```
    
        
    
    
