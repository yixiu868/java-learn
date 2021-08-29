package com.ww.jvm.oom;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaohua
 * @description -XX:MetaspaceSize=6M -XX:MaxMetaspaceSize=6M
 * @date 2021-8-26 16:49
 */
public class RuntimeConstantPoolOOM2 {

    public static void main(String[] args) {
        // 保持着对字符串常量池引用，避免FGC回收
        Set<String> set = new HashSet<>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
