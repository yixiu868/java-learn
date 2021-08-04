package com.ww.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * String[] File.list()方法遍历目录下所有文件、目录，返回String类型数组
 */
public class FileListDemo {

    private static FilenameFilter onlyExt;

    public static void main(String[] args) throws IOException {
        String basePath = "D:\\test\\kettle";
        File file = new File(basePath);

        // travel(file);

        onlyExt = new OnlyExt("ktr");

        travel2(file);
    }

    /**
     * 递归遍历目录，不进行文件过滤
     * @param file
     * @throws IOException
     */
    public static void travel(File file) throws IOException {
        if (file.isDirectory()) {
            System.out.println("目录1:" + file.getAbsolutePath());
            // list()只是返回当前目录下，文件名、目录名，如若要访问当前File，再需要加上父文件夹进行拼接
            String[] fileList = file.list();

            for (String tmpFile : fileList) {
                File file1 = new File(file.getAbsolutePath(), tmpFile);
                if (file1.isFile()) {
                    System.out.println("文件1:" + file1.getAbsolutePath());
                } else {
                    // System.out.println("目录2:" + file1.getCanonicalPath());
                    travel(file1);
                }
            }
        } else {
            System.out.println("文件2:" + file.getAbsolutePath());
        }
    }


    public static void travel2(File file) {
        if (file.isDirectory()) {
            System.out.println("目录1:" + file.getAbsolutePath());
            // list()只是返回当前目录下，文件名、目录名，如若要访问当前File，再需要加上父文件夹进行拼接
            String[] fileList = file.list(onlyExt);

            for (String tmpFile : fileList) {
                File file1 = new File(file.getAbsolutePath(), tmpFile);
                if (file1.isFile()) {
                    System.out.println("文件1:" + file1.getAbsolutePath());
                } else {
                    // System.out.println("目录2:" + file1.getCanonicalPath());
                    travel2(file1);
                }
            }
        } else {
            System.out.println("文件2:" + file.getAbsolutePath());
        }
    }
}

/**
 * 实现FilenameFilter接口，list()进行文件过滤
 */
class OnlyExt implements FilenameFilter {

    private String ext;

    public OnlyExt(String ext) {
        this.ext = "." + ext;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(ext);
    }
}