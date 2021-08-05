package com.ww.generic;

/**
 * @Description: 泛型类
 * 
 * 泛型的类型参数只能是类类型，不能是简单类型
 * 
 * @author xiaohua
 * @date 2021年8月4日 下午10:39:39
 */
public class Generic <T> {

	private T key;
	
	public Generic(T key) {
		this.key = key;
	}

	/**
	 * 虽然在方法中使用了泛型，但是这并不是一个泛型方法
	 * 这只是类中一个普通的成员方法，只不过他返回的值是在声明泛型类已经声明过的泛型
	 * @return
	 */
	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}
	
	/**
	 * 在泛型类中声明了一个泛型方法，使用泛型E表示类型，这种泛型E可以为任意类型。可以与T相同，也可以与T不同。
	 * @param <E>
	 * @param t
	 */
	public <E> void show(E t) {
		System.out.println(t.toString());
	}
}
