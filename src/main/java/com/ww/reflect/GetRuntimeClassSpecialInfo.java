package com.ww.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: 调用运行时类的指定结构
 * @author xiaohua
 * @date 2021年8月5日 下午12:18:34
 */
public class GetRuntimeClassSpecialInfo {

    /**
     * 调用指定属性
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @SuppressWarnings({ "deprecation" })
    public static void getSpecialField() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
        Class<Customer> clazz = Customer.class;
        Customer customer = clazz.newInstance();
        // 获取运行时类中指定字段
        Field name = clazz.getDeclaredField("name");
        // 设置属性可访问
        name.setAccessible(true);
        // 设置属性值
        name.set(customer, "Tokyo");
        System.out.println(name.get(customer));
    }
    
    /**
     * 获取指定方法
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @SuppressWarnings({ "deprecation" })
    public static void getSpecialMethod() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Class<Customer> clazz = Customer.class;
        Customer customer = clazz.newInstance();
        
        // 获取指定方法名方法
        Method method = clazz.getDeclaredMethod("setName", String.class);
        // 设置为可访问
        method.setAccessible(true);
        
        method.invoke(customer, "China");
        System.out.println(customer.getName());
    }
    
    /**
     * 获取指定构造器
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void getSpecialConstructor() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<Customer> clazz = Customer.class;
        Constructor<Customer> constructor = clazz.getDeclaredConstructor(String.class, Integer.class);
        // 设置可访问
        constructor.setAccessible(true);
        Customer customer = constructor.newInstance("China", 1);
        System.out.println(customer);
    }
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
//        getSpecialField();
        
//        getSpecialMethod();
        
        getSpecialConstructor();
    }
}
