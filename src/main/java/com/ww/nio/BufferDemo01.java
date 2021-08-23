package com.ww.nio;

import java.nio.*;

/**
 * @author xiaohua
 * @description Buffer基本功能使用
 * @date 2021-8-23 23:19
 */
public class BufferDemo01 {

    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1, 2, 3};
        short[] shortArray = new short[]{1, 2, 3, 4};
        int[] intArray = new int[]{1, 2, 3, 4, 5};
        long[] longArray = new long[]{1, 2, 3, 4, 5, 6};
        float[] floatArray = new float[]{1, 2, 3, 4, 5, 6, 7};
        double[] doubleArray = new double[]{1, 2, 3, 4, 5, 6, 7, 8};
        char[] charArray = new char[]{'a', 'b', 'c', 'd'};

        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        ShortBuffer shortBuffer = ShortBuffer.wrap(shortArray);
        IntBuffer intBuffer = IntBuffer.wrap(intArray);
        LongBuffer longBuffer = LongBuffer.wrap(longArray);
        FloatBuffer floatBuffer = FloatBuffer.wrap(floatArray);
        DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubleArray);
        CharBuffer charBuffer = CharBuffer.wrap(charArray);

        System.out.println("byteBuffer\t=\t" + byteBuffer.getClass().getName());
        System.out.println("shortBuffer\t=\t" + shortBuffer.getClass().getName());
        System.out.println("intBuffer\t=\t" + intBuffer.getClass().getName());
        System.out.println("longBuffer\t=\t" + longBuffer.getClass().getName());
        System.out.println("floatBuffer\t=\t" + floatBuffer.getClass().getName());
        System.out.println("doubleBuffer\t=\t" + doubleBuffer.getClass().getName());
        System.out.println("charBuffer\t=\t" + charBuffer.getClass().getName());

        System.out.println();

        System.out.println("byteBuffer.capacity\t=\t" + byteBuffer.capacity());
        System.out.println("shortBuffer.capacity\t=\t" + shortBuffer.capacity());
        System.out.println("intBuffer.capacity\t=\t" + intBuffer.capacity());
        System.out.println("longBuffer.capacity\t=\t" + longBuffer.capacity());
        System.out.println("floatBuffer.capacity\t=\t" + floatBuffer.capacity());
        System.out.println("doubleBuffer.capacity\t=\t" + doubleBuffer.capacity());
        System.out.println("charBuffer.capacity\t=\t" + charBuffer.capacity());
    }
}
