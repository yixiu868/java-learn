# String

## String常量池

* 直接使用双引号声明出来的String对象会直接存储在常量池中；
* 如果不是用双引号声明的String对象，可以使用String提供的intern方法。intern方法会从字符串常量池中查找当前字符串是否存在，若不存在就会将当前的字符串放入常量池中。

### 问题1 创建了几个对象

```
String s = new String("abc");
```

答案，创建了2个对象，第一个对象是"abc"字符串存储在常量池中；第二个对象是Java Heap中创建的String对象。

