package com.ww.java7.path;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author wanggw
 * @description Path路径方法示例
 * @date 2020/5/23 17:42
 */
public class Path001 {

    @SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
        // 指定目录Path
        // 方法1：多个参数
        // Windows下使用\拼接，Linux下使用/拼接
        Path absolute = Paths.get("/", "home", "wanggw"); // 绝对路径
        Path relative = Paths.get("home", "wanggw", "env.properties"); // 相对路径
        // 方法2：一个字符串
        Path homeDirectory = Paths.get("/home/wanggw");

        // 组合或解析方法resolve()
        // 规则：
        // 1、如果q是一个绝对路径，那么返回的结果是q
        // 2、否则，根据文件系统规则，结果就是“p然后q”
        // 程序需要找到相对于主目录的配置文件
        Path configPath = homeDirectory.resolve("myprog/conf/user.properties");
        // 等同于
        configPath = homeDirectory.resolve(Paths.get("myprog/conf/user.properties"));

        // resolveSibling，针对某个路径的父目录进行解析，并产生一个兄弟路径
        Path workPath = Paths.get("/home/cay/myprog/work");
        Path tempPath = workPath.resolveSibling("temp"); // 返回路径为：/home/cay/myprog/temp

        // relativize()会产生一个相对路径
        Paths.get("/home/cay").relativize(Paths.get("/home/fred/myprog")); // 返回 ../fred/myprog

        // normalize()方法会去掉所有冗余的.和..

        // toAbsolutePath()方法会返回指定路径的绝对路径
        Path p = Paths.get("D:\\Git\\learnfun\\javalearn\\src\\main\\java\\com\\ww\\java7\\feature\\ResourceAutoClose.java");
        Path parent = p.getParent(); // 获取路径 D:\Git\learnfun\javalearn\src\main\java\com\ww\java7\feature
        System.out.println(parent);
        Path file = p.getFileName(); // 获取文件名 ResourceAutoClose.java
        System.out.println(file);
        Path root = p.getRoot(); // D:\
        System.out.println(root);

        // Path转为File，方法toFile()
        File file2 = p.toFile();
        System.out.println("路径:" + file2.getAbsolutePath() + ", filename:" + file2.getName());

        // Files类可以快速实现一些常用的文件操作
        // 读写实用好方法
        // 一次性读取所有内容为字节数组
        byte[] bytes = Files.readAllBytes(p);
        String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("一次性读取所有内容为字节数组, 再以UTF-8格式转为字符串:" + content);

        // 按行读取文件
        List<String> lines = Files.readAllLines(p);
        System.out.println("按行读入lines,然后forEach遍历:");
        lines.forEach(x -> System.out.println(x));

        // write写入文件
        Path p2 = Paths.get("D:\\Git\\learnfun\\javalearn\\src\\main\\java\\com\\ww\\java7\\feature\\path\\path.txt");
        Files.write(p2, content.getBytes(StandardCharsets.UTF_8));
        // 追加模式
        String[] arrays = { "wanggw", "books" };
        Files.write(p2, Arrays.asList(arrays), StandardOpenOption.APPEND);

        // 读写字节流、字符流
        InputStream in = Files.newInputStream(p2);
        OutputStream out = Files.newOutputStream(p2);
        Reader reader = Files.newBufferedReader(p2);
        Writer writer = Files.newBufferedWriter(p2);

        // InputStream中的内容保存到一个文件中
        Files.copy(in, p2);
        // 可以将某个文件的内容复制到一个OutputStream中
        Files.copy(p2, out);

        // 创建文件和目录
        Files.createDirectory(p2);
        // 创建多级目录
        Files.createDirectories(p2);
        // 创建一个空文件
        // 如果文件已经存在，该调用会抛出一个异常，由于对于已有文件的检查和创建都属于一个原子操作
        // 如果文件不存在，那么它一定会在其他线程之前创建该文件
        Files.createFile(p2);

        // Files类提供了一些方便的方法，用来在由用户或系统指定的位置创建临时文件或目录
        Path newPath1 = Files.createTempFile(p2, "", "");
        Path newPath2 = Files.createTempFile("", "");
        Path newPath3 = Files.createTempDirectory(p2, "");
        Path newPath4 = Files.createTempDirectory("");

        // 要取一个目录下的文件，可以使用Files.list, Files.walk

        // 复制、移动、删除
        // 将某个文件从一个位置复制到另一个位置
        Files.copy(p, p2);
        // 移动一个文件(即复制并删除原来的文件)
        Files.move(p, p2);
        // 如果目标文件或目录存在的话，copy或move方法会失败，如果你希望覆盖一个已存在的目标文件，
        // 请使用REPLATE_EXISTING选项，如果复制所有的文件属性，请使用COPY_ATTRIBUTES选项
        Files.copy(p, p2, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        // 可以指定使用原子方式执行移动操作，这样要么移动操作成功完成，要么源文件依然存在，要指定原子操作可以使用
        // ATOMIC_MOVE
        Files.move(p, p2, StandardCopyOption.ATOMIC_MOVE);
        // 要删除一个文件，只需调用
        Files.delete(p);
        // 如果文件不存在，则该方法会抛出一个异常，因此你可能希望使用另一个方法
        // 该方法可以用于删除一个空目录
        // 没有直接删除或复制非空目录的方法
        boolean deleted = Files.deleteIfExists(p);

    }
}
