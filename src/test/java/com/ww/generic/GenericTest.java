package com.ww.generic;

import org.junit.Test;

/**
 * @Description: 泛型测试
 * @author xiaohua
 * @date 2021年8月4日 下午11:09:00
 */
public class GenericTest {

	/**
	 * 泛型类测试
	 */
	@Test
	public void genericClassTest() {
		Generic<Integer> genericInt = new Generic<>(12345);
		Generic<String> genericStr = new Generic<>("hello");
		System.out.println("泛型类，key is " + genericInt.getKey());
		System.out.println("泛型类，key is " + genericStr.getKey());
	}
}
