package com.ww.jvm.init;

/**
 * @author xiaohua
 * @description 演示类实例化顺序
 * @date 2021-8-26 12:24
 *
 * ①父类静态成员和静态初始化块，按在代码中出现的顺序依次执行
 * ②子类静态成员和静态初始化块，按在代码中出现的顺序依次执行
 * ③父类实例成员和实例初始化块，按在代码中出现的顺序依次执行
 * ④父类构造方法
 * ⑤子类实例成员和实例初始化块，按在代码中出现的顺序依次执行
 * ⑥子类构造方法
 */
public class Dervied extends Base {

    private String name = "hello";

    public Dervied() {
        tellName();
        printName();
    }

    public void tellName() {
        System.out.println("Dervied tell name: " + name);
    }

    public void printName() {
        System.out.println("Dervied print name: " + name);
    }

    /**
     * 打印结果
     * Dervied tell name: null
     * Dervied print name: null
     * Dervied tell name: hello
     * Dervied print name: hello
     * @param args
     */
    public static void main(String[] args) {
        new Dervied();
    }
}

class Base {

    private String name = "公众号";

    public Base() {
        tellName();
        printName();
    }

    public void tellName() {
        System.out.println("Base tell name: " + name);
    }

    public void printName() {
        System.out.println("Base print name: " + name);
    }
}