package com.ww.nio;

import java.nio.*;

/**
 * @author xiaohua
 * @description TODO
 * @date 2021-8-23 23:55
 */
public class BufferIsReadOnlyDemo06 {

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

        System.out.println("byteBuffer.isReadOnly = " + byteBuffer.isReadOnly());
        System.out.println("shortBuffer.isReadOnly = " + shortBuffer.isReadOnly());
        System.out.println("intBuffer.isReadOnly = " + intBuffer.isReadOnly());
        System.out.println("longBuffer.isReadOnly = " + longBuffer.isReadOnly());
        System.out.println("floatBuffer.isReadOnly = " + floatBuffer.isReadOnly());
        System.out.println("doubleBuffer.isReadOnly = " + doubleBuffer.isReadOnly());
        System.out.println("charBuffer.isReadOnly = " + charBuffer.isReadOnly());
    }
}
