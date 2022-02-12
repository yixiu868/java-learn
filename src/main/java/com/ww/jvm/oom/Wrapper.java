package com.ww.jvm.oom;

import java.util.Map;
import java.util.Random;

/**
 * @Description: 测试GC overhead limit exceeded
 * 启动设置
 * java -Xmx10m -XX:+UseParallelGC
 * 
 * @author xiaohua
 * @date 2021年9月22日 下午1:19:52
 */
public class Wrapper {

	/**
	 * 
	 * @Title: main
	 * @Description: 错误信息
	 * 
	 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
	at java.base/java.lang.Integer.valueOf(Integer.java:1050)
	at com.ww.jvm.oom.Wrapper.main(Wrapper.java:22)
	 * 
	 * @param args
	 * @return void 
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		Map map = System.getProperties();
		
		Random random = new Random();
		while (true) {
			map.put(random.nextInt(), "value");
		}
	}
}
