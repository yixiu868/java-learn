package com.ww.jvm.oom;

/**
 * @Description: 测试小内存放入大对象，造成heap OOM
 * 设置运行时参数 -Xmx12m
 * 
 * 错误信息
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *	at com.ww.jvm.oom.OOM.main(OOM.java:16)
 * 
 * @author xiaohua
 * @date 2021年9月22日 上午11:01:54
 */
public class OOM {

	static final int SIZE = 8 * 1024 * 1024;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int[] i = new int[SIZE];
		System.out.println("程序执行成功.");
	}
}
