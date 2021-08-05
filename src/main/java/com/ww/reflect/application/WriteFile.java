package com.ww.reflect.application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    private static String pathname = "D:/test/out.txt";
    
    public static void write(StringBuilder sb) throws IOException {
        File file = new File(pathname);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(sb.toString());
        bufferedWriter.close();
    }
}
