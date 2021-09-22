package com.ww.collection.dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 测试不同时重写hashCode、equals方法造成问题
 * @author xiaohua
 * @date 2021年9月22日 上午11:53:06
 */
public class HashCodeAndEqualsQuestion {
	
	private static Map<Object, Integer> roomManager = new HashMap<>();
	
	/**
	 * HashMap缓存中获取
	 * @Title: countRoom
	 * @Description: 
	 * @param user
	 * @return void 
	 * @throws
	 */
	public static void countRoom(User user) {
		boolean exist = roomManager.containsKey(user);
		if (exist) {
			// 存在则计数器增加
			Integer num = roomManager.get(user);
			roomManager.put(user, ++num);
		} else {
			// 不存在则插入，并且计数器置为1
			roomManager.put(user, 1);
		}
		System.out.println("姓名:" + user.getName() + "，身份证id:" + user.getIdCard() + "拥有" + roomManager.get(user) + "套房");
	}

	public static void main(String[] args) {
		User user = new User("小王", "12345");
		countRoom(user);
		System.out.println(user.hashCode());
		
		User user2 = new User("小王", "12345");
		countRoom(user2);
		System.out.println(user2.hashCode());
		
		System.out.println(user.equals(user2));
		
		// 重写hashCode方法前打印结果
//		姓名:小王，身份证id:12345拥有1套房
//		姓名:小王，身份证id:12345拥有1套房
		
		// 重写hashCode方法后打印结果
//		姓名:小王，身份证id:12345拥有1套房
//		70357591
//		姓名:小王，身份证id:12345拥有2套房
//		70357591
//		true

	}
}

class User {
	
	private String name;
	
	private String idCard;
	
	public User() {}
	
	public User(String name, String idCard) {
		this.name = name;
		this.idCard = idCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	/**
	 * 重写equals，认为身份证相同就是同一人
	 */
	@Override
	public boolean equals(Object obj) {
		// 重写equals步骤
		// 1、判断是否同一个实例
		// 2、通过instanceof判断类型是否相同
		// 3、转换为正确类型
		// 4、比较各属性值是否相同
		
		if (this == obj) {
			return true;
		}
		
		// 类型不匹配
		if (!(obj instanceof User)) {
			return false;
		}
		
		// 强转类型
		User user = (User)obj;
		
		// 比较属性值
		return this.name.equals(user.getName()) 
			&& this.idCard.equals(user.getIdCard());
	}
	
	/**
	 * 重写hashCode可以使用JDK自带Objects提供的静态方法
	 */
	@Override
	public int hashCode() {
//		int result = name != null ? name.hashCode() : 0;
//		result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
//		return result;
		
		return Objects.hash(name, idCard);
	}
}