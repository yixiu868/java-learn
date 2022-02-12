# TLAB

[ Java堆内存是线程共享的！面试官：你确定吗？](https://mp.weixin.qq.com/s/Wws24Fhg1nH4dHvtcFYi2g)

## Java对象分配内存如何保证线程安全

![d7e0be1dfadbf39f7dd9166bd4e01196_640_wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1](img\d7e0be1dfadbf39f7dd9166bd4e01196_640_wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1.webp)

## HotSpot解决方案

```java
每个线程在Java堆中预先分配一小块内存，然后再给对象分配内存的时候，直接在自己这块【私有】内存中分配，当这部分区域用完之后，再分配新的【私有】内存。
```

Thread Local Allocation Buffer，从堆中划分出来，是本地线程独享的。

## TLAB

虚拟机在堆内存的eden划分出来的一块专用空间，是线程专属的。在虚拟机的TLAB功能启动情况下，在线程初始化时，虚拟机会为每个线程分配一块TLAB空间，只给当前线程使用，如果需要分配内存，就在自己的空间上分配，这样就不存在竞争的情况，可以大大提升分配效率。

`ちゅうい`

这里值得注意的是，TLAB是线程独享的，但是只是在【分配】这个动作上是线程独享的，至于读取、垃圾回收等动作上都是线程共享的。而且在使用上也没有区别。

![4eb5ace0269a033d17dbcbce5fc1d30c_640_wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1](img\4eb5ace0269a033d17dbcbce5fc1d30c_640_wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1.webp)

有一点需要注意

TLAB是在Eden区分配的，因为Eden区域本身就不大，而且TLAB空间的内存也非常小，默认情况下仅占整个Eden空间的`1%`，所以，必然存在一些`大对象`是无法在TLAB上直接分配的。这时就需要进行【同步控制】，小的对象比大的对象分配起来更加高效。

