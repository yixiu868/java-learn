package com.ww.string;

/**
 * @author xiaohua
 * @description String常量池
 * 参考链接
 * https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html
 *
 * jdk7版本对intern操作和常量池做了一定的修改，主要包括：
 * 1、将String常量池从Perm区移动到Java Heap区；
 * 2、String#intern方法时，如果存在堆中的对象，会直接保持对象的引用，而不会重新创建对象
 *
 * @date 2021-8-25 14:39
 */
public class StringDemo01 {

    public static void main(String[] args) {
        // 执行Heap分配Java对象
        // 并在常量池中创建"1"
        String s = new String("1");
        s.intern();

        // 执行字符串常量池
        String s2 = "1";
        // 结果：false
        System.out.println(s == s2);

        System.out.println("-----------------");

        String s3 = new String("1") + new String("1");
        // s3把"11"放入字符串常量池
        // 如果把s3.intern()注释掉，“11”就不会被放入字符串常量池
//        s3.intern();
        // 接下来s3.intern();这一句代码，是将 s3中的“11”字符串放入 String 常量池中，因为此时常量池中不存在“11”字符串，因此常规做法是跟 jdk6 图中表示的那样，在常量池中生成一个 “11” 的对象，关键点是 jdk7 中常量池不在 Perm 区域了，这块做了调整。常量池中不需要再存储一份对象了，可以直接存储堆中的引用。这份引用指向 s3 引用的对象。 也就是说引用地址是相同的。
        s3.intern();

        // s4指向字符串常量池
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
