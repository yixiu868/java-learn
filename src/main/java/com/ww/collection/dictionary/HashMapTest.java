package com.ww.collection.dictionary;

import java.util.HashMap;

/**
 * @Description: 测试HashMap分别以不同类型为key
 * @author xiaohua
 * @date 2021年9月22日 下午12:25:09
 */
public class HashMapTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		HashMap<Object, Object> map = new HashMap<>();
		
		// 以String作为key
		map.put("a", "abc");
		System.out.println("key为a，值" + map.get("a"));
		map.put("a", "bac");
		System.out.println("key为a，值" + map.get("a"));
		System.out.println("--------------------");
		
		// 以Integer作为key，且范围在-128--127
		Integer ai = new Integer(100);
		map.put(ai, 100);
		System.out.println("ai.hashCode=" + ai.hashCode());
		System.out.println("key为100，值" + map.get(ai));
		Integer bi = new Integer(100);
		map.put(bi, 1);
		System.out.println("bi.hashCode=" + bi.hashCode());
		System.out.println("key为100，值" + map.get(bi));
		System.out.println("--------------------");
		
		// 以Integer作为key，且范围不在-128--127
		Integer ai2 = new Integer(200);
		map.put(ai2, 100);
		System.out.println("ai2.hashCode=" + ai2);
		System.out.println("key为100，值" + map.get(ai2));
		Integer bi2 = new Integer(100);
		map.put(bi2, 1);
		System.out.println("bi2.hashCode=" + bi2);
		System.out.println("key为100，值" + map.get(bi2));
		System.out.println("--------------------");
	}
}
