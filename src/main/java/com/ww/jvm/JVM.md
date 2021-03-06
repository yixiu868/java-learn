## 类加载

![v2-b9d39568c0e3f87a5df6a0cbfe753cda_r](img\v2-b9d39568c0e3f87a5df6a0cbfe753cda_r.jpg)

加载.class文件大致可以分为3个步骤：

* 1、检查是否已经加载，有就直接返回，避免重复加载；
* 2、当前缓存中确实没有该类，那么遵循双亲委派机制，加载.class文件；
* 3、上面两步都失败了，调用findClass()方法加载；

```java
Class类的构造器是私有的，无法手动new一个Class对象，只能由JVM创建，JVM在构造Class对象时，需要传入一个类加载器（类加载器中的defineClass方法自动构造的），然后完成上述一连串的加载、创建过程。   
    
    在运行期间，一个类，只有一个Class对象产生。
```



* 问题：调用method.invoke(obj, args)，为什么要传入一个目标对象？

  方法是共用的，JVM如何保证p1调用changeUser()时，changeUser()不会跑去把p2的数据改掉？

  所以JVM设置了一种隐性机制，每次对象调用方法时，都会**隐性传递**当前调用该方法的**对象参数**，方法可以根据这个对象参数知道当前调用本方法的是哪个对象。

### 类加载过程详解

![20210805145958](img\20210805145958.png)

Java虚拟机规范指出，必须调用初始化场景：

* 1、使用new关键字实例化对象的时候；
* 2、读取或设置一个类型的静态字段的时候；
* 3、调用一个类型的静态方法的时候；
* 4、使用java.lang.reflect包的方法对类型进行反射调用的时候；
* 5、当初始化类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化；
* 6、当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类；
* 7、当一个接口定义了JDK8新加入的默认方法（被default修改）时，如果有这个接口的实现类发生了初始化，那么该接口要在其之前被初始化。
  * 区别：当一个接口在初始化时，并不要求其父接口全部都完成了初始化，只有在真正使用到父接口的时候才会初始化。

#### 加载

需要完成以下三件事：

* 1、通过类名找到class文件，获取字节数组；
* 2、将字节数组转化为方法区的运行时数据结构；
* 3、在`堆内存中构建一个Class对象`，作为方法区这个类的各种数据访问的入口。

#### 连接

##### 验证

验证是连接阶段的第一步，这一阶段的目的是确保Class文件的字节流中包含的信息符合《Java虚拟机规范》的全部约束。

* 1、文件格式验证
  * 是否以魔数cafe babe开头；
  * 主、次版本号是否在当前Java虚拟机接收范围之内；
  * 。。。
* 2、元数据验证
  * 这个类是否有父类；
  * 这个类的父类是否继承了不允许被继承的类；
  * 如果不是抽象类，是否实现了其父类或接口之中要求实现的所有方法；
  * 。。。
* 3、字节码验证
  * 保证任何跳转指令都不会跳转到方法体以外的字节码指令上；
  * 。。。
* 4、符号引用验证
  * 符号引用中通过字符串描述的全限定名是否能找到对应的类；
  * 。。。

##### 准备

准备阶段是正式为类中定义的**静态变量（被static修饰的变量）分配内存**并设置类变量初始值的阶段。从概念上讲，这些变量所使用的内存都应当在方法区中进行分配，但是必须注意到方法区本身是一个逻辑上的区域，JDK7及之前，HotSpot使用永久代来实现该方法区，在**JDK8及之后，`类变量则会随着Class对象一起存放在Java堆中`**。

```java
注意：
    1、这里说的仅包括类变量，不包括实例变量；
    2、这里所说的初始值“通常情况”下是数据类型的零值；
    例如：
    public static int value = 123;
	变量value在准备阶段过后的初始值为0而不是123，因为这个时候尚未开始执行任何Java方法。把value赋值为123的动作要到类的初始化阶段才会被执行。
```

##### 解析

解析阶段是Java虚拟机将常量池内的符号引用替换为直接引用的过程。

#### 初始化

直到初始化阶段，Java虚拟机才真正开始执行类中编写的Java程序代码，将主导权移交给应用程序。

初始化阶段就是执行类构造器`<clinit>()`方法的过程。

`<clinit>()`方法由编译器自动收集类中的所有`类变量的赋值动作和静态代码块合并产生的`。

`<clinit>()`方法与类的构造方法不同，不需要显示地调用父类构造器，Java虚拟机会保证在子类的`<clinit>()`方法执行前，父类的`<clinit>()`方法已经执行完毕。

```java
Java虚拟机必须保证一个类的<clinit>()方法在多线程环境中被正确地枷锁同步，如果多个线程同时去初始化一个类，那么只会有其中一个线程去执行这个类的<clinit>()方法，其他线程阻塞。
```

### 面试题：Java从编译到执行，发生了什么（一页科技）

4个步骤，编译->加载->解释->执行

#### 编译

将源码文件编译成JVM可以解释的class文件。

编译过程会对源码程序做**语法分析、语义分析、注解处理**等，最后才生成字节码文件。比如对泛型的擦除就是在编译阶段干的。

#### 加载

把编译后的class文件加载到JVM中

分为装载->连接->初始化

##### 装载

* `装载时机`

  为了节省内存的开销，并不会一次性把所有的类都装载至JVM，等到有需要的时候才进行装载（比如new、反射等）

* `装载发生`

  class文件是通过类加载器装载到JVM中的，**为了防止内存中出现多份同样的字节码**，使用了双亲委派机制

* `装载规则`

  JDK中的本地方法类一般由根加载器(Bootstrp loader)装载，JDK中内部实现的扩展类一般由扩展加载器(ExtClassLoader)实现装载，而程序中的类文件则由应用加载器(AppClassLoader)装载。

`总结`

装载这个阶段做的事情总结：

查找并加载类的字节码，在JVM堆中创建一个java.lang.Class类的对象，并将类的相关信息存储在JVM方法区中。

##### 连接

连接做的事情，总结一句话：对class信息进行`验证`，为`类变量分配内存`空间并对其赋默认值。

###### 验证

验证类是否符合Java规范和JVM规范

###### 准备

为类静态变量分配内存，初始化为系统的初始值（比如int为0，字符串为空）

###### 解析

将符号引用转为直接引用

##### 初始化

这个阶段总结：为类的静态变量赋予正确的初始值

过程大概是：收集class的`静态变量、静态代码块、静态方法`至clinit()方法，随后从上往下开始执行。

#### 解释

初始化完成之后，构造对象实例，当我们尝试执行一个类的方法时，会找到对应方法的字节码信息，然后解释器会把**字节码信息解释成系统能识别的指令码**。

会有两种方式把字节码信息解释成机器指令码，一个是字节码解释器、一个是即时编译器（JIT）

JVM会对`热点代码`做编译，非热点代码直接进行解释。当JVM发现某个方法或代码块的运行特别频繁的时候，就有可能把这部分代码认定为`热点代码`。

热点探测一般有两种方式，计数器和抽样。

#### 执行

操作系统把解释器解析出来的指令码，调用系统的硬件执行最终的程序指令。

![640_06](img\640_06.webp)

## JVM都有哪些常量池

参考链接

[String常量池深度解析](https://www.zhihu.com/question/55994121)

### Class文件中的常量池

主要存放两大类常量：

* ①字面量：文本字符串等
* ②符号引用
  * 类和接口的全限定名
  * 字段的名称和描述符
  * 方法的名称和描述符

### 运行时常量池

方法区的一部分，`存放 编译期 生成的各种字面量和符号引用`，这部分内容将`在类加载后进入方法区`的运行时常量池。

### 全局字符串常量池

HotSpot VM里，记录interned string的一个全局表叫做StringTable，本质上就是`HashSet<String>`。这是个纯运行时的结构，而且是惰性维护的。注意它只存储对java.lang.String**`实例的引用`**。而不存储String对象的内容。`注意了只存储了引用，根据这个引用可以得到具体的String对象`。

#### 字面量进入字符串常量池的时机

HotSpot VM实现来说，`加载类的时候`，那些字符串字面量会进入到当前类的运行时常量池，不会进入全局的字符串常量池（即在StringTable中没有相应的引用，在堆中也没有对应的对象产生）。

```java
// JDK1.7+
class NewTest1 {
    // 第一句
    public static String s1 = "static";
    
    public static void main(String[] args) {
        // 第二句
        String s1 = new String("he") + new String("llo");
        // 第三句
        s1.intern();
        // 第四句
        String s2 = "hello";
        // 第五句 输出为true
        System.out.println(s1 == s2);
    }
}
```

##### 分析：

"static"，"he"，"llo"都会进入Class的常量池，按照上面说的，类加载阶段由于resolve阶段是lazy的，所以是不会创建实例，更不会驻留字符串常量池了。但是要注意这个"static"和其他三个不一样，它是静态的，在类加载阶段中的初始化阶段，会为静态变量指定初始值，也就是要把"static"赋值给s1，那么这个赋值操作要怎么搞？显然需要先ldc指令把它放到栈顶，然后用putstatic指令完成赋值。注意，ldc指令，根据上面说的，会创建"static"字符串对象，并且会保存一个指向它的引用到字符串常量池。

运行main方法后，首先是第二句，一样的，要先用ldc把"he"和"llo"送至栈顶，换句话说，会创建他两个的对象，并且会保存引用到字符串常量池中；然后有个+号，内部是创建了一个StringBuilder对象，一路append，然后调用StringBuilder对象的toString()方法得到一个String对象，并把它赋值给s1。注意，没有把hello的引用放入字符串常量池。

第三局，intern方法一看，字符串常量池里面没有"hello"，它会把上面的这个hello对象的引用保存到字符串常量池，然后返回这个引用，但是这个返回值我们并没有使用变量去接收，所有没用。

第四句，字符串常量池里面已经有了，直接使用。

## JVM内存结构

![640](img\640_01.webp)

### 程序计数器

Java是多线程的语言，假设线程数大于CPU数，很可能有**线程切换**现象，切换意味着**中断**和**恢复**，那自然需要一块区域来保持当前线程的执行信息。

程序计数器就是用于记录各个线程执行的`字节码地址`（分支、循环、调整、异常、线程恢复等都需要依赖计数器）。

### 虚拟机栈

每个线程在创建的时候都会创建一个虚拟机栈，每次方法调用都会创建一个栈帧，每个栈帧会包含几块内容：`局部变量表、操作数栈、动态链接和返回地址`。

![640_02](img\640_02.webp)

虚拟机栈作用：保存方法局部变量、参与部分变量计算以及方法调用和返回。

### 本地方法栈

虚拟机栈用于管理Java函数的调用，本地方法栈用于管理本地方法的调用，非Java程序，一般本地方法是指C或C++程序。

### 方法区

方法区只是JVM中规范的一部分而已。

HotSpot虚拟机，常常提到永久代，在JDK8前使用永久代实现了方法区，在JDK8中，使用`元空间`替代了永久代作为`方法区实现`。

方法区主要用来存放已经被虚拟机加载的**类相关信息**：包括类信息、常量池

* `类信息`包括类的版本、字段、方法、接口和父类等信息；
* `常量池`又分为**静态常量池**和**运行时常量池**；
  * **静态常量池**主要存储是字面量、符号引用；静态常量池也包括了**字符串常量池**；
  * **运行时常量池**存储的是类加载时生成的**直接引用**等信息

#### `注意`

从**逻辑分区**的角度而言**常量池属于方法区**，从**物理分区**来说`运行时常量池和静态常量池属于堆`。

但自从JDK7以后，就已经把**运行时常量池和静态常量池**移到了**堆中存储**

![640_03](img\640_03.webp)

### 堆

![640_04](img\640_04.webp)

## Java内存模型

### 为什么会有Java内存模型

Java为了屏蔽硬件和操作系统访问内存的各种差异，提出了`Java内存模型`的规范，保证了Java程序在各种平台下对内存的访问都能得到一致效果。

### Java内存模型主要内容

#### Java内存模型的抽象结构

线程之间的**共享变量**存储在**主内存**中，每个线程都有自己私有的**本地内存**，**本地内存**存储了该线程以读、写共享变量的副本。

本地内存是Java内存模型的抽象概念，并不真实存在。

![640_05](img\640_05.webp)

Java内存模型规定，线程对变量的所有操作都必须在**本地内存进行**，**不能直接读写主内存**。

#### happen-before规则

目的是为了阐述操作之间的**内存可见性**

说白了就是某些重要的场景下，这一组操作不能进行重排序，前面一个操作的结果对后续操作必须是可见的。

规则举例，volatile变量规则、程序顺序规则、监视器锁规则等。

#### 对volatile内存语义的探讨

主要作用是可见性和有序性（禁止重排序）

说白了，volatile前后加上`内存屏障`，使得编译器和CPU无法进行指令重排，并且写volatile变量对其他线程是可见的。

## 垃圾回收机制

### 可达性分析

从GC Roots开始向下搜索，当对象到GC Roots都没有任何引用相连时，说明对象是不可用的，可以被回收

#### GC Roots指什么东西

##### 栈帧局部变量表

JVM内存结构中的虚拟机栈，虚拟机栈里有栈帧，栈帧里有局部变量，局部变量存储着引用。

如果栈帧位于虚拟机的栈顶，说明这个栈帧是活跃的，栈帧指向堆中的对象引用也是活跃的，堆里对象引用就可以是GC Roots

##### 静态变量引用

类的静态变量引用是GC Roots

总结：GC Roots是一组必须活跃的引用，只要跟GC Roots没有直接或间接引用相连，那就是垃圾对象，需要被回收。

### 垃圾收集算法

* 标记清除
* 复制
* 标记压缩（标记整理）

![640_07](img\640_07.webp)

#### 新生代老年代

##### 新创建的对象一般是在新生代，什么时候会到老年代

* ①如果对象太大了，就会直接进入老年代（对象创建时很大或者Survivor区域没办法存下该对象）。
* 如果对象太老了，就会晋升到老年代（每发生一次Minor GC，存活的对象年龄加1，达到默认值15则晋升老年代或者动态对象年龄判定，可以进入老年代）

##### 什么时候触发Yong GC

当Eden区空间不足时，就会触发Yong GC

##### 新生代GC的时候，从GC Roots出发，那不也会扫描到老年代的对象吗，不就相当于全堆扫描了吗

HotSpot虚拟机（G1以下）要求整个GC堆在连续的地址空间上，所以有一条分界线（一侧是老年代，另一侧是新生代），所以通过地址就可以判断对象在哪个分代上。

当做Yong GC时候，从GC Roots出发，如果发现老年代的对象，那就不往下走了。

##### 又有一个问题，如果新生代的对象被老年代引用了，Yong GC的时候肯定不能回收掉该新生代对象的

HotSpot虚拟机下有个`card table（卡表）`来避免全局扫描老年代对象，堆内存的每一个小块区域形成`卡页`，卡表实际上是卡页的集合。当判断一个卡页中存在对象的跨代引用时，将这个页标记为`脏页`。

每次Yong GC的时候需要去卡表找到脏页，找到后加入至GC Root，就不用去遍历整个老年代对象了。

## JVM常见题

### JVM出现full GC很频繁，线上问题排查考虑点

按照触发full GC的条件来做

* JDK8之前的永久代太小，或JDK8之后的元空间太小，或者程序bug，导致动态生成了非常多新类；
* System.gc()方法调用；
* 内存泄漏，频繁创建大量对象，但是无法被回收，最终会先触发FGC，再导致OOM；
* 大对象，系统一次性加载了过多数据到内存中，导致这些大对象直接分配在了老年代；



* 优化新生代、老年代大小方法
  * 如果一次full GC后，剩余对象不多，说明Eden区设置太小，导致短生命周期对象进入了Old区；
  * 如果一次full GC后，Old区回收率不大，说明Old区太小；

### 排查GC问题使用工具

* 全方位监控JVM各项指标；

* JDK自带工具

  * 查看Java进程：jps

  * 查询堆内存各区域的使用率以及GC情况

    ```bash
    jstat -gcutil -h20 pid
    ```

  * 查看堆内存中存活对象，并按空间排序

    ```bash
    jmap -histo pid | head -n20
    ```

  * dump堆内存文件

    ```bash
    jmap -dump:format=b,file=heap pid
    ```

  * 可视化堆内存分析工具：JVisualVM，MAT

### 排查思路指南

* ①查看监控，以了解出现问题的时间点以及当前FGC的频率，对比下平时是否正常；
* ②了解该时间点之前有没有程序上线、基础组件升级等情况；
* ③了解JVM的参数设置，包括
  * 对空间各个区域的大小设置；
  * 新生代老年代分别采用哪种垃圾收集器；
  * 分析JVM参数设置是否合理；
* 再对可能的原因做排除法，其中元空间是否打满，内存是否泄漏，代码是否显示调用gc；
* 针对大对象或者生命周期长对象的FGC，可通过jmap -histo命令并集合dump文件作进一步分析，需要定位到可疑对象。
* 再通过可疑对象定位到具体代码，这个时候要结合GC原理和JVM参数设置，弄清楚可疑对象是否满足了进入老年代条件。

### GC对程序产生影响大小

影响严重程度从高到低

* ①FGC过于频繁：FGC通常是比较慢的，少则几百毫秒，多则几秒，正常情况FGC`每隔几个小时甚至几天才执行一次`。一旦出现FGC频繁（比如`几十分钟就会执行一次`），这种肯定是存在问题的，导致工作线程频繁停止，影响程序的整体性能。
* ②YGC耗时过长：一般来说，YGC总耗时在几十或者上百毫秒是比较正常的，对程序影响可以忽略不计。如果`YGC耗时达到1秒或者几秒`，甚至赶上了FGC的耗时，再加上YGC本身比较频繁，就会导致比较多的服务耗时问题了。
* FGC耗时过长
* YGC过于频繁

## 新生代GC变化

![285763-20161118103751232-177696421](img\285763-20161118103751232-177696421.png)

因为新生代中的对象级别都是朝生夕死的（80%以上），所以在新生代的垃圾回收算法是复制算法，复制算法的基本思想是将内存分为两块，每次只用其中一块，当这一块内存用完，就将还活着的对象复制到另外一块上面，不会产生内存碎片。

在GC开始的时候，对象只会存在Eden区和名为"From"的Survivor区，Survivor区"To"是空的。紧接着进行GC，Eden区中所有存活的对象都会被复制到"To"，而在"From"区中的，仍活着的对象也会根据他们的年龄决定去向。年龄达到阈值会被移动到老年代中，没有达到阈值的对象会被复制到"To"区域。经过这次GC后，Eden区和From区已经被清空，这个时候"From"和"To"会交换他们的角色。不管怎么样都会保证名为To的区域是空的。YGC一直重复着这个过程。

## 垃圾收集器

### G1

G1开创的基于Region的堆内存布局是它能够实现这个目标的关键。

G1不再坚持固定大小以及固定数量的分代区域划分，而是把连续的Java堆划分成为多个大小相等的独立区域（Region），`每一个Region`都可以根据需要，扮演新生代的`Eden空间、Survivor空间，或者老年代空间`。收集器能够对扮演不同角色的Region采用不同的策略去处理。

Region还有一类特殊的`Humongous区域`，专门用来存储大对象。G1认为只要大小超过了一个Region容量的一半就认为是大对象。`超大对象`，使用多个连续Humongous区域存储，G1大多数行为都把Humongous Region作为老年代的一部分看待。

G1收集器建立可预测的停顿时间模型。Region作为单次回收的最小单元，每次收集到的空间都是Region大小整数倍。可以避免对整个Java堆进行垃圾收集。

`具体思路`让G1收集器去跟踪各个Region里面的垃圾堆的价值大小，价值就是指回收到的空间大小以及回收需要时间，在后台维护一个优先级列表。

## JVM定位问题工具

参考链接

[JVM故障分析及性能优化系列](https://www.javatang.com/archives/2017/10/19/33151873.html)

### jstack

jstack是Java虚拟机自带的一种堆栈跟踪工具。可以对活着的进程进行本地或远程线程的dump。

`主要目的`：定位线程出现长时间停顿的原因，比如线程间死锁、死循环、请求外部资源导致的长时间等待等。

参考链接

[java命令--jstack 工具](https://www.cnblogs.com/kongzhongqijing/articles/3630264.html)

```java
"AsyncFileHandlerWriter-1259475182" #10 daemon prio=5 os_prio=0 tid=0x00007f5a0c11d800 nid=0x29fe waiting on condition [0x00007f59fa22c000]
   java.lang.Thread.State: TIMED_WAITING (parking)
        at sun.misc.Unsafe.park(Native Method)
        - parking to wait for  <0x00000000c521e108> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
        at java.util.concurrent.LinkedBlockingDeque.pollFirst(LinkedBlockingDeque.java:522)
        at java.util.concurrent.LinkedBlockingDeque.poll(LinkedBlockingDeque.java:684)
        at org.apache.juli.AsyncFileHandler$LoggerThread.run(AsyncFileHandler.java:160)

"Service Thread" #7 daemon prio=9 os_prio=0 tid=0x00007f5a0c0bd000 nid=0x29fa runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread1" #6 daemon prio=9 os_prio=0 tid=0x00007f5a0c0ba800 nid=0x29f8 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #5 daemon prio=9 os_prio=0 tid=0x00007f5a0c0b7800 nid=0x29f6 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=0 tid=0x00007f5a0c0b6000 nid=0x29f5 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=0 tid=0x00007f5a0c083000 nid=0x29f3 in Object.wait() [0x00007f59faf5b000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000000c4c26820> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
        - locked <0x00000000c4c26820> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
        at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)

"Reference Handler" #2 daemon prio=10 os_prio=0 tid=0x00007f5a0c07e000 nid=0x29f0 in Object.wait() [0x00007f59fb05c000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000000c4c26860> (a java.lang.ref.Reference$Lock)
        at java.lang.Object.wait(Object.java:502)
        at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
        - locked <0x00000000c4c26860> (a java.lang.ref.Reference$Lock)
        at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"main" #1 prio=5 os_prio=0 tid=0x00007f5a0c00a000 nid=0x29e7 runnable [0x00007f5a14771000]
   java.lang.Thread.State: RUNNABLE
        at java.net.PlainSocketImpl.socketAccept(Native Method)
        at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:535)
        at java.net.ServerSocket.implAccept(ServerSocket.java:545)
        at java.net.ServerSocket.accept(ServerSocket.java:513)
        at org.apache.catalina.core.StandardServer.await(StandardServer.java:447)
        at org.apache.catalina.startup.Catalina.await(Catalina.java:776)
        at org.apache.catalina.startup.Catalina.start(Catalina.java:722)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:343)
        at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:474)

```

### jstat

[jstat使用详解](https://www.cnblogs.com/sxdcgaq8080/p/11089841.html)

[jstat工具使用](https://www.cnblogs.com/kongzhongqijing/articles/3625574.html)

用于监控`虚拟机各种运行状态`信息的命令行工具，监视JVM内存工具。

用以判断JVM是否存在内存问题？如何判断JVM垃圾回收是否正常？

#### 统计gc信息

![dae4970634a2869494f1be5c756084fa_301156076411757](img\dae4970634a2869494f1be5c756084fa_301156076411757.jpg)

### jmap

主要用于打印指定Java进程共享对象内存映射或堆内存细节。

可以获得运行中的`jvm的堆快照`，从而可以离线分析堆，以检查`内存泄漏`，检查一些`严重影响性能的对象的创建`，检查系统中什么对象最多，各种对象所占内存大小等等；还可以使用jmap生成`Heap Dump`。

## 内存溢出

参考链接

[JVM 中发生内存溢出的 8 种原因及解决办法](https://juejin.cn/post/6844903974970064904)

### 1、Java堆空间

* 原因：
  * 无法在Java堆中分配对象；
  * 吞吐量增加；
  * 应用程序无意中保存了对象引用，对象无法被GC回收；
* 解决方案
  * 使用-Xmx增加堆大小；
  * 修复应用程序中的内存泄漏；

### 2、GC开销超过限制

* 原因
  * Java 进程98%的时间在进行垃圾回收，恢复了不到2%的堆空间，最后连续5个（编译时常量）垃圾回收一直如此
