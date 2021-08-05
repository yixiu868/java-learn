package com.ww.reflect;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class ReflectCopyTest {

    @Test
    public void test01() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("安心");
        customer.setAge(20);
        
        Customer target = new ReflectCopyUtils().copy(customer);
        System.out.println(target);
    }
}
