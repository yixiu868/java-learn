package com.ww.generic;

import java.util.Random;

/**
 * @Description: 传入泛型实参
 * 定义一个生产器实现这个接口，虽然我们只创建了一个泛型接口Generator<T>
 * 但是我们可以为T传入无数个实参，形成无数种类型的Generator接口
 * Generator<T> public T next();中的T都要替换成传入的String类型
 * @author xiaohua
 * @date 2021年8月4日 下午11:24:55
 */
public class Vegetable implements Generator<String> {

	// 蔬菜
	private String[] vegetables = new String[] { "pepper", "tomato", "bean", "beet" };

	@Override
	public String next() {
		Random random = new Random();
		return vegetables[random.nextInt(4)];
	}
}
