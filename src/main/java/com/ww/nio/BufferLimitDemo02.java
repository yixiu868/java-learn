package com.ww.nio;

import java.nio.CharBuffer;

/**
 * @author xiaohua
 * @description Buffer limit练习，代表第一个不应该读取或写入元素的index
 * @date 2021-8-23 23:33
 */
public class BufferLimitDemo02 {

    public static void main(String[] args) {
        char[] charArray = new char[]{'a', 'b', 'c', 'd', 'e'};
        CharBuffer charBuffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity() = " + charBuffer.capacity() + " limit() = " + charBuffer.limit());

        // 重置limit
        charBuffer.limit(3);

        System.out.println();
        System.out.println("B capacity() = " + charBuffer.capacity() + " limit = " + charBuffer.limit());

        charBuffer.put(0, 'o');
        charBuffer.put(1, 'p');
        charBuffer.put(2, 'q');
        // 从3不允许写入
        // 报错Exception in thread "main" java.lang.IndexOutOfBoundsException
        charBuffer.put(3, 'r');
        charBuffer.put(4, 's');
        charBuffer.put(5, 't');
        charBuffer.put(6, 'u');
    }
}
