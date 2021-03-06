# jmm

## 为什么需要JMM

### 背景

* 1、现在计算机往往是多核的，每个核心下会有高速缓存，由于CPU与内存的速度存在差异，所以产生了高速缓存，L1和L2缓存一般是每个核心独有的；
* 2、为了让CPU提高运算效率，处理器可能会对输入的代码进行`指令重排`；
* 3、一次对数值的修改往往不是原子性的，比如i++；

### 带来问题

* 1、缓存数据不一致：多个线程同时修改`共享变量`，CPU核心下的高速缓存是不共享的，那多个cache与内存直接的数据如何进行同步？
* 2、CPU指令重排在多线程下会导致代码在非预期下执行，最终会导致结果存在错误的情况；

### 解决办法

#### 针对缓存一致性

* 1、使用`总线锁`：某个核心在修改数据的过程中，其他核心均无法修改内存中的数据，效率低下；

* 2、缓存一致性协议（MESI协议）；

  缓存一致性协议，可以理解为`缓存锁`，针对`缓存行`进行加锁，所谓缓存行其实就是高速缓存存储的`最小单位`。

  MESI协议原理大概就是：当每个CPU读取共享变量之前，会先识别数据【`对象状态`】（是修改、还是共享、还是独占，还是无效）。

  如果是`独占`，说明当前CPU将要得到的变量数据是最新的，没有被其他CPU所同时读取。

  如果是`共享`，说明当前CPU将要得到的变量数据还是最新的，有其他的CPU在同时读取，但还没被修改。

  如果是`修改`，说明当前CPU正在修改该变量的值，同时会向其他CPU发送该数据状态为invalid（无效）的通知，得到其他CPU响应后，会当前CPU将高速缓存的数据写到主存，并把自己的状态从modify（修改）修改为exclusive（独占）。

  如果是`无效`，说明当前数据是被修改过了，需要从主存重写读取最新的数据。

  **`关键是`**在于某个CPU在对数据进行修改时，需要【同步】通知其他CPU，表示这个数据被我修改了，你们不能使用了。

### 内存屏障

内存屏障可以分为三种类型：写屏障，读屏障以及全能平展（包含了读写屏障）

屏障可以简单理解为：在操作数据的时候，往数据插入一条【特殊的指令】，只要遇到这个指令，那前面的操作都得【完成】。

`写屏障`

CPU当发现写屏障的指令时，会把该指令【之前】存在于【Store Buffer】所有写指令刷入高速缓存。

通过这种方式可以让CPU修改的数据可以马上暴露给其他CPU，达到【写操作】可见性效果。

`读屏障`

CPU当发现读屏障的指令时，会把该指令【之前】存在于【invalid queue】所有的指令都处理掉。

通过这种方式可以确保当前CPU的缓存状态是准确的，达到【读操作】一定是读最新的效果。

### 总结

由于不同CPU架构的缓存体系不一样，缓存一致性协议也不一样，指令重排策略也不一样，为了简化Java开发人员的工作，Java提出了一套规范【Java内存模型】。

屏蔽各种硬件和操作系统的访问差异，保证Java程序可移植性。

`目的`

解决多线程存在的原子性、可见性以及有序性问题。

## 什么是JMM

### 模块

* 1、Java内存模型的抽象结构；
* 2、happen-before规则；
* 3、对volatile内存语义的探讨；

### Java内存模型

线程之间的【共享变量】存储在【主存】中，每个线程都有自己私有的【本地内存】，【本地内存】存储了该线程以读、写共享变量的副本。

本地内存是Java内存模型的抽象概念，非真实存在的。

![3cf5d96522eb036b147bc2dd671b533d_640_wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1](img\3cf5d96522eb036b147bc2dd671b533d_640_wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1.webp)

`规定`：线程对变量的多有操作都必须在【本地内存】进行，【不能直接读写主内存】的变量。

Java内存模型定义了8中操作来完成【变量如何从主内存到本地内存，以及变量如何从本地内存到主内存】

![3badb3fe4285ee36d2e7189abcd616f9_640_wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1](img\3badb3fe4285ee36d2e7189abcd616f9_640_wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1.webp)

### happen-before

在某些重要的场景下，这一组操作都不能进行指令重排【前面一个操作的结果对后续操作必须可见的】。

### volatile

主要特性：可见性、有序性

Java内存模型为了实现volatile有序性和可见性，定义了内存屏障。

在volatile【前后】加上【内存屏障】，使得编译器和CPU无法进行指令重排，保证有序，并且写volatile变量对其他线程可见。