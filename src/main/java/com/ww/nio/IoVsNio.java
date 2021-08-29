package com.ww.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xiaohua
 * @description NIO学习
 * 参考链接 https://blog.csdn.net/forezp/article/details/88414741/
 * @date 2021-8-27 15:36
 */
public class IoVsNio {

    /**
     * Buffer的使用，一般遵循以下步骤
     * 1、分配空间（ByteBuffer buf = ByteBuffer.allocate(1024);）
     * 2、写入数据到Buffer（int bytesRead = fileChannel.read(buf);）
     * 3、调用flip()方法（buf.flip();）
     * 4、从Buffer中读取数据（System.out.print((char)buf.get());）
     * 5、调用clear()方法或者compact()方法
     *
     * 向Buffer中写数据
     * Channel写到Buffer（fileChannel.read(buf)）
     * 通过Buffer的put()方法（buf.put()）
     *
     * 从Buffer中读取数据
     * 从Buffer读取到Channel（channel.write(buf)）
     * 使用get()方法从Buffer中读取数据（buf.get()）
     */
    public static void nioTest() {
        RandomAccessFile accessFile = null;

        try {
            accessFile = new RandomAccessFile("D:/test/abc.txt", "rw");
            FileChannel channel = accessFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int byteRead = channel.read(buf);
            System.out.println(byteRead);

            while (-1 != byteRead) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char)buf.get());
                }
                buf.compact();
                byteRead = channel.read(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != accessFile) {
                try {
                    accessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 采用FileInputStream读取文件内容
     */
    public static void ioTest() {
        InputStream input = null;

        try {
            input = new BufferedInputStream(new FileInputStream("D:/test/abc.txt"));
            byte[] buf = new byte[1024];
            int bytesRead = input.read(buf);
            while (-1 != bytesRead) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char)buf[i]);
                }

                bytesRead = input.read(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
//        ioTest();
        nioTest();
    }
}
