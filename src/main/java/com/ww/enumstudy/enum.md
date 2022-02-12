# 枚举

> 参考资料
>
> [深入理解Java枚举类型(enum)](https://blog.csdn.net/javazejian/article/details/71333103)

## 枚举实现原理

实际上使用关键字enum创建枚举类型并编译后，编译器会为我们生成一个相关的类，这个类继承了Java API的java.lang.Enum类，也就是说通过关键字enum创建枚举类型在编译后事实上也是一个类类型而且该类继承自Java.lang.Enum类。

```java
public enum Day {
    
    // 值一般是大写的字母，多个值之间以逗号分隔
    
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}
```

javac编译Day.java文件生成了Day.class，反编译代码如下

```java
//反编译Day.class
final class Day extends Enum
{
    //编译器为我们添加的静态的values()方法
    public static Day[] values()
    {
        return (Day[])$VALUES.clone();
    }
    //编译器为我们添加的静态的valueOf()方法，注意间接调用了Enum也类的valueOf方法
    public static Day valueOf(String s)
    {
        return (Day)Enum.valueOf(com/ww/enumstudy/Day, s);
    }
    //私有构造函数
    private Day(String s, int i)
    {
        super(s, i);
    }
     //前面定义的7种枚举实例
    public static final Day MONDAY;
    public static final Day TUESDAY;
    public static final Day WEDNESDAY;
    public static final Day THURSDAY;
    public static final Day FRIDAY;
    public static final Day SATURDAY;
    public static final Day SUNDAY;
    private static final Day $VALUES[];

    static 
    {    
        //实例化枚举实例
        MONDAY = new Day("MONDAY", 0);
        TUESDAY = new Day("TUESDAY", 1);
        WEDNESDAY = new Day("WEDNESDAY", 2);
        THURSDAY = new Day("THURSDAY", 3);
        FRIDAY = new Day("FRIDAY", 4);
        SATURDAY = new Day("SATURDAY", 5);
        SUNDAY = new Day("SUNDAY", 6);
        $VALUES = (new Day[] {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
        });
    }
}
```

编译器还为我们生成了两个静态方法，分别是values()和valueOf()

### 枚举与Class对象

当枚举实例向上转型为Enum类型后，values()方法将会失效，也就无法一次性获取所有枚举实例变量，由于Class对象的存在，即使不适用values()方法，还是有可能一次获取到所有枚举实例变量的。

```java
// 获取所有枚举值
Day[] days = Day.values();
// 向上转型
Enum e = Day.MONDAY;
Class<?> claz = e.getDeclaringClass();
if (claz.isEnum()) {
    Day[] dsz = (Day[])claz.getEnumConstants();
    System.out.println("dsz:" + Arrays.toString(dsz));
}
```

### 枚举进阶用法

实际上enum定义的枚举类，除了不能使用继承，可以把enum类当成常规类，可以向enum类中添加方法和变量。

```java
public enum Day02 {

    MONDAY("a"),
    TUESDAY("b"),
    WEDNESDAY("c"),
    THURSDAY("d"),
    FRIDAY("e"),
    SATURDAY("f"),
    SUNDAY("g");
    
    private String code;
    
    private Day02(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
```

