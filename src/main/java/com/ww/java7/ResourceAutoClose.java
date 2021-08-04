package com.ww.java7;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author wanggw
 * @description TODO
 * @date 2020/5/23 16:57
 */
public class ResourceAutoClose {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(Paths.get("D:\\Git\\learnfun\\javalearn\\src\\main\\java\\com\\ww\\java7\\feature\\ResourceAutoClose.java"))) {
            while (in.hasNext()) {
                System.out.print(in.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
