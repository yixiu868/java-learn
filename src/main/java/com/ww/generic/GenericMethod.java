package com.ww.generic;

/**
 * @Description: 泛型方法
 * 
 * 静态方法与泛型：ちゅうい，静态方法无法访问类上定义的类型，如果静态方法操作的引用数据类型不确定的时候，必须将泛型定义在方法上，总之如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法。
 * 
 * @author xiaohua
 * @date 2021年8月4日 下午11:41:07
 */
public class GenericMethod {

	/**
	 * 泛型方法
	 * 
	 * 首先在public与返回值之间的<T>必不可少，这表明是一个泛型方法
	 * 
	 * @param <T>
	 * @param container
	 * @return
	 */
	public <T> T showKeyName(Generic<T> container) {
		T key = container.getKey();
		return key;
	}
	
	/**
	 * 不是一个泛型方法
	 * @param obj
	 */
	public void showKeyValue1(Generic<Number> obj) {
		System.out.println("方法" + obj.getKey());
	}
	
	/**
	 * 不是一个泛型方法
	 * 如果是?表示可以放Object类型以及他的子类
	 * @param obj
	 */
	public void showKeyValue2(Generic<?> obj) {
		System.out.println("方法" + obj.getKey());
	}
	
	/**
	 * 泛型方法，可变参数
	 * @param <T>
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public <T> void showMsg(T... args) {
		for (T t : args) {
			System.out.print(t + ",");
		}
		System.out.println();
	}
	
	/**
	 * 静态方法与泛型
	 * @param <T>
	 * @param t
	 */
	public static <T> void show(T t) {
		System.out.println("静态方法，" + t.toString());
	}
	
	/**
	 * 设置泛型类型上下边界
	 * @param obj
	 */
	public void showKeyValue3(Generic<? extends Number> obj) {
        System.out.println("泛型方法(Number子类)，" + obj.getKey());
    }
}
