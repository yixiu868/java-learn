# OOM

[JVM内存泄露（OOM）的9种类型](https://www.anvilliu.com/2021/04/01/JVM%E5%86%85%E5%AD%98%E6%B3%84%E9%9C%B2%EF%BC%88OOM%EF%BC%89%E7%9A%849%E7%A7%8D%E7%B1%BB%E5%9E%8B/)

[介绍JVM中OOM的8种类型](https://tianmingxing.com/2019/11/17/%E4%BB%8B%E7%BB%8DJVM%E4%B8%ADOOM%E7%9A%848%E7%A7%8D%E7%B1%BB%E5%9E%8B/)

`代码位置：com.ww.jvm.oom`

## 1-Java heap space

### 原因

* 1）用户量或数据量激增，超过预期阈值；
* 2）内存泄漏；

### 示例见代码

com.ww.jvm.oom.OOM

### 解决方案

* 1）堆内存不足，分配更大内存；
* 2）内存泄漏，定位问题代码，解决代码问题；

## 2-GC overhead limit exceeded

### 原因

应用程序花费太多时间做垃圾收集，收集内存太少。默认情况下，如果JVM花费的时间超过总时间的98%，并且在GC之后仅恢复了不到2%的堆内存，将其配置为引发此错误。

### 示例见代码

### 解决方案

如果希望解决Java堆空间的根本问题而不是掩盖症状，则需要弄清楚代码的哪一部分负责分配最多的内存。需要搞清楚以下问题：

* 1、哪些对象占据堆的大部分；
* 2、这些对象在源代码中的分配位置；

在官方文档中，提到可以使用-XX:-UseGCOverheadLimit类禁用这个报错，但是显然是治标不治本的做法，这样做会造成以下问题：

* 1）会造成很大的延迟，处理速度明细变慢，甚至卡死；
* 2）到一定时间后会报出`java.lang.OutOfMemoryError: Java heap space`错误，从而影响判断；

因此不建议添加-XX:-UseGCOverheadLimit启动。

## 3-Permgen space

### 原因

造成该错误的主要原因是永久代中转入了太多的类或太大的类。

### 示例见代码

```java
import javassist.ClassPool;

public class MicroGenerator {
    
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100_000_000; i++) {
            generate("eu.plumbr.demo.Generated" + i);
        }
    }
    
    public static Class generate(String name) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        return pool.makeClass(name).toClass();
    }
}
```



### 解决方案

* 1）将-XX:MaxPermSize设置大一些；
* 2）可以对JVM重启，当然最根本的解决办法还是查看代码里的问题，往往代码中的大量反射操作会导致反射生成大量代理类，让永久代内存不足，所以说从代码上优化才是最根本的解决办法。

## 4-Metaspace
### 原因

错误`java.lang.OutOfMemoryError: Metaspace`错误，这个错误和永久代错误类似。

### 示例见代码

### 解决方案

* 1）设置-XX:MaxMetaspaceSize设置更大值；
* 2）最好的办法还是优化代码，检查创建类数量的代码位置；

## 5-Unable to create new native thread
### 原因

线程达到可启动的上限

### 示例见代码

### 解决方案

* 1）使用线程池。因为线程池会限制最大线程数；

* 2）通过-Xss限制线程栈大小；

* 3）查看操作系统是否对JVM的线程创建有所限制；

  ```bash
  ulimit -a
  ```

  

## 6-Out of swap space
### 原因

当JVM请求的总内存大于可用物理内存时，操作系统会开始把数据从内存中换到硬盘中。当数据将交换空间填满，并且物理空间也被填满之后会报这个错误。

### 示例见代码

### 解决方案

可以增加交换空间的大小，但是对于Java的垃圾回收而言，交换是不希望发生的，因为交换过后的内存分配`可能让GC暂停时间增加几个数量`级。尽量避免别的进程和主要服务端程序竞争内存资源。也可以进行内存升级以从根本上增加内存大小。

## 7-Request array size exceeds VM limit
### 原因

Java对可用分配的数组大小有限制，当遇到请求的数组大小大于JVM限制时，就会报此错误。

### 示例见代码

```java
class Test {
    
    public static void main(String[] args) throws Exception {
        for (int i = 3; i >= 0; i--) {
            try {
                int[] a = new int[Integer.MAX_VALUE - ];
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 解决方案

限制数组大小，尽量不要用一个特别大的数组去储存，可以分成较小的多个数组批量加载需要的数据。

## 8-Kill process or sacirfice child
### 原因

内核任务，内存不足，在可用内存极低的情况下会杀死进程

### 示例见代码

### 解决方案

