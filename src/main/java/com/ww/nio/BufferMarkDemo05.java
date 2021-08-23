package com.ww.nio;

import java.nio.ByteBuffer;

/**
 * @author xiaohua
 * @description mark()，标记一个索引，在调用reset()时，会将缓冲区的position位置重置为该索引
 * @date 2021-8-23 23:50
 */
public class BufferMarkDemo05 {

    public static void main(String[] args) {
        byte[] byteArray = {1, 2, 3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

        System.out.println("byteBuffer.capacity() = " + byteBuffer.capacity());

        byteBuffer.position(1);
        // 在位置1设置mark
        byteBuffer.mark();

        System.out.println("byteBuffer.position = " + byteBuffer.position());

        // 改变position
        byteBuffer.position(2);
        System.out.println("byteBuffer.position = " + byteBuffer.position());
        // 位置重置
        byteBuffer.reset();
        System.out.println("byteBuffer.position = " + byteBuffer.position());
    }
}
