package com.ww.java8;

import java.util.Arrays;

/**
 * @author wanggw
 * @description TODO
 * @date 2020/5/23 15:46
 */
public class Lambda001 {

    public static void main(String[] args) {
        Integer[] array = { 5, 2, 7, 2, 8, 9, 10 };

        System.out.println("排序前:");
        System.out.println(Arrays.asList(array));

        Arrays.sort(array, Integer::compareTo);

        System.out.println("排序后");
        System.out.println(Arrays.asList(array));

        // 测试自定义函数式接口
        display(() -> System.out.println("接口调用成功了"));

        // 另一种写法；方法引用 对象::实例方法
        Test001 test001 = new Test001_01();
        new Lambda001().display2(test001::display);

        // 非抽象方法是不能使用lambda表达式的
        // 运行结果打印的依然为:hello
        print(() -> System.out.println("abc"));
    }

    static void display(Test001 test001) {
        test001.display();
    }

    static void print(Test001 test001) {
        test001.print();
    }

    public void display2(Test001 test001) {
        test001.display();
    }
}

@FunctionalInterface
interface Test001 {

    void display();

    default void print() {
        System.out.println("hello");
    }
}

class Test001_01 implements Test001 {

    public void display() {
        System.out.println("Test001_01.display()");
    }
}