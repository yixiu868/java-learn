package com.ww.reflect;

/**
 * @Description: 获取Class实例的方式
 * @author xiaohua
 * @date 2021年8月5日 上午11:55:19
 */
public class GetClassWays {

    /**
     * 方法1：.class
     */
    public static void way01() {
        Class<Customer> clazz = Customer.class;
        System.out.println(clazz);
    }
    
    /**
     * 方法2：getClass()
     */
    public static void way02() {
        Customer customer = new Customer();
        Class<? extends Customer> clazz = customer.getClass();
        System.out.println(clazz);
    }
    
    /**
     * 方法3：forName()
     * @throws ClassNotFoundException
     */
    public static void way03() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.ww.reflect.Customer");
        System.out.println(clazz);
    }
    
    /**
     * 方法4：ClassLoader
     * @throws ClassNotFoundException
     */
    public static void way04() throws ClassNotFoundException {
        Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("com.ww.reflect.Customer");
        System.out.println(clazz);
    }
}
