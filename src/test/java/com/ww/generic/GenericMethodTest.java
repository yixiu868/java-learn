package com.ww.generic;

import org.junit.Test;

public class GenericMethodTest {

	@Test
	public void test01() {
		GenericMethod genericMethod = new GenericMethod();
		Generic<String> genericStr = new Generic<>("hello world");
		
		// 測試泛型方法
		String keyName = genericMethod.showKeyName(genericStr);
		System.out.println(keyName);
		System.out.println("-------------------");
		
		// 測試普通方法
		genericMethod.showKeyValue1(new Generic<Number>(123));
		System.out.println("-------------------");
		
		// 測試<?>，也不是泛型方法，可以当做Object使用
		genericMethod.showKeyValue2(new Generic<>("dream"));
		genericMethod.showKeyValue2(new Generic<>(123));
		System.out.println("-------------------");
		
		// 泛型类为String类型，泛型访问为Number类型
		genericStr.show(123);
		System.out.println("-------------------");
		
		// 泛型方法与可变参数测试
		genericMethod.showMsg("123", 312, "aaa", "55.6", 88.6);
		System.out.println("-------------------");
		
		// 静态方法与泛型（无法使用泛型类中类型，必须写入静态方法）
		GenericMethod.show("world");
		GenericMethod.show(123);
		
		// 泛型上下边界
		genericMethod.showKeyValue3(new Generic<>(123));
		genericMethod.showKeyValue3(new Generic<>(1.25));
		genericMethod.showKeyValue3(new Generic<>(1.2f));
		// Cannot infer type arguments for Generic<>
//		genericMethod.showKeyValue3(new Generic<>("123"));
	}
}
