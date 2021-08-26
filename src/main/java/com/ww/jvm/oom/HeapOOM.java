package com.ww.jvm.oom;

import java.util.ArrayList;

/**
 * @author xiaohua
 * @description 虚拟机参数：-verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError
 * @date 2021-8-26 16:18
 */
public class HeapOOM {

    static class OOMObject {}

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
