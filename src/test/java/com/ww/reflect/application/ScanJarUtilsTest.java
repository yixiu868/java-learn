package com.ww.reflect.application;

import java.io.IOException;

import org.junit.Test;

public class ScanJarUtilsTest {

    @Test
    public void test01() throws IOException {
        String jar = "D:\\Program Files\\Java\\jdk-11.0.6\\lib\\jrt-fs.jar";
        ScanJarUtils.getJar(jar);
    }
}
