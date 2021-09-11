# Linux-CPU

## 如果CPU使用率达到100%，怎么排查

参考链接

[告诉你如何回答"线上CPU100%排查"面试问题](https://www.cnblogs.com/xichji/p/11713300.html)

[三个实例演示Java Thread Dump日志分析](https://www.cnblogs.com/zhengyun_ustc/archive/2013/01/06/dumpanalysis.html)

步骤：

1. top拿到占用cpu比较高的进程

2. top -HP拿到进程中的线程pid

3. jastack 对应pid | grep pid转十六进制（或者自己去文件找）

   

* 1、通过`top`找到占用率高的进程

  ![e1355bfa3a9e0bdcb7e2d58199d005df_640_wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1&retryload=1](img\e1355bfa3a9e0bdcb7e2d58199d005df_640_wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1&retryload=1.webp)

* 2、通过`top -Hp pid`找到占用CPU高的线程ID。

  ![d2d02f21f8a9de2bf18777ae95fb8324_640_wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1&retryload=1](img\d2d02f21f8a9de2bf18777ae95fb8324_640_wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1&retryload=1.webp)

* 3、把线程ID转为16进制，得到16进制线程ID

* 4、通过命令`jstack 163 | grep '0x3be' -C5 --color`或者`jstack 163 | vim +/0x3be -`找到有问题代码

  ![28ca5ba4dc94efa31e25cc9324aa7755_640_wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1&retryload=1](img\28ca5ba4dc94efa31e25cc9324aa7755_640_wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1&retryload=1.webp)