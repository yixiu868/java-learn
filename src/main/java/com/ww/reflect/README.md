## 反射
功能如下：
* 1、在运行时判断任意一个对象所属的类；
* 2、在运行时构造任意一个类的对象；
* 3、在运行时判断任意一个类所具有的成员变量和方法；
* 4、在运行时调用任意的一个对象的方法；
* 5、在运行时处理注解；
* 6、生成动态代理；

## 类加载

![image-20200730161708535](img\aHR0cHM6Ly9naXRlZS5jb20vb3lfY2hhcnRfYmVkL25vMV9kcmF3aW5nX2JlZC9yYXcvbWFzdGVyLzIwMjAwNzMwMTYxNzE1LnBuZw.png)

* 类加载的作用：将class字节码文件内容加载到内存中，并将这些静态数据**转换成方法区的运行时数据结构**，然后在堆中生成一个代表这个类的java.lang.Class对象，作为方法区中类数据的访问入口。
