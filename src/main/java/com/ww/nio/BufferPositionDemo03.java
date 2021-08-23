package com.ww.nio;

import java.nio.CharBuffer;

/**
 * @author xiaohua
 * @description Buffer position()：下一个读取或写入操作的index
 * @date 2021-8-23 23:38
 */
public class BufferPositionDemo03 {

    public static void main(String[] args) {
        char[] charArray = {'a', 'b', 'c', 'd'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity() = " + buffer.capacity() + " limit() = " + buffer.limit() + " position = " + buffer.position());

        // 设置position
        buffer.position(2);

        System.out.println("B capacity() = " + buffer.capacity() + " limit() = " + buffer.limit() + " position = " + buffer.position());

        buffer.put('z');
        for (char ch : charArray) {
            System.out.print(ch + " ");
        }
    }
}
