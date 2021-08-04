package com.ww.generic;

/**
 * @Description: 泛型类
 * @author xiaohua
 * @date 2021年8月4日 下午10:39:39
 */
public class Generic <T> {

	private T key;
	
	public Generic(T key) {
		this.key = key;
	}
	
	public T getKey() {
		return key;
	}
}
