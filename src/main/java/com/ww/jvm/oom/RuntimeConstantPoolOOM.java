package com.ww.jvm.oom;

/**
 * @author xiaohua
 * @description String.inern()
 * @date 2021-8-26 16:49
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        // true
        System.out.println(str1.intern() == str1);


        String str2 = new StringBuilder("ja").append("va").toString();
        // 而对str2比较返
        //回false， 这是因为“java”[2]这个字符串在执行String-Builder.toString()之前就已经出现过了， 字符串常量
        //池中已经有它的引用， 不符合intern()方法要求“首次遇到”的原则
        // false
        System.out.println(str2.intern() == str2);
    }
}
