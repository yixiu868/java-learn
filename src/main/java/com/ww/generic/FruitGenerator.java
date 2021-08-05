package com.ww.generic;

/**
 * @Description: 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需要将泛型的声明也一起加到类中
 * 如果不声明泛型，如：class FruitGenerator implements Generator<T>，编译器会报错"Unknown class"
 * @author xiaohua
 * @date 2021年8月4日 下午11:18:40
 */
public class FruitGenerator<T> implements Generator<T> {

	@Override
	public T next() {
		return null;
	}

}
