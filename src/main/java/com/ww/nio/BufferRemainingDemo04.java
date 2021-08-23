package com.ww.nio;

import java.nio.CharBuffer;

/**
 * @author xiaohua
 * @description remaining()：返回"当前位置"与limit之间的元素数
 * @date 2021-8-23 23:43
 */
public class BufferRemainingDemo04 {

    public static void main(String[] args) {
        char[] charArray = {'a', 'b', 'c', 'd', 'e'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity() = " + buffer.capacity() + " limit() = " + buffer.limit() + " position = " + buffer.position());
        // 设置position
        buffer.position(2);
        System.out.println("A capacity() = " + buffer.capacity() + " limit() = " + buffer.limit() + " position = " + buffer.position());
        System.out.println("C remaining() = " + buffer.remaining());
    }
}
