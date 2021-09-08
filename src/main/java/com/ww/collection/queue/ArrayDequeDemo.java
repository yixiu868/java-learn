package com.ww.collection.queue;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * ArrayDeque基本用法
 * @author xiaohua
 *
 */
public class ArrayDequeDemo {

	public static void main(String[] args) {
		ArrayDeque<String> arrayDeque = new ArrayDeque<>();
		arrayDeque.add("a");
		arrayDeque.add("b");
		arrayDeque.add("c");
		
		Iterator<String> iterator = arrayDeque.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		
		arrayDeque.offer("d");
		arrayDeque.offer("e");
		arrayDeque.offer("f");
		
		System.out.println("-----------------");
		System.out.println(arrayDeque.pollLast());
		System.out.println(arrayDeque.pollLast());
		System.out.println(arrayDeque.pollLast());
	}
}
