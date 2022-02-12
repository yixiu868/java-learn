package com.ww.enumstudy;

import java.util.Arrays;

import org.junit.Test;

public class DayTest {

    @Test
    public void test01() {
        Day day = Day.FRIDAY;
        System.out.println(day.name());
        System.out.println(day.toString());  
        System.out.println(day.ordinal());
        
        // 自定义值
        Day02 day02 = Day02.MONDAY;
        System.out.println("------------");
        System.out.println(day02.name());
        System.out.println(day02.toString());     
        System.out.println(day02.getCode());   
        System.out.println(day02.ordinal());
        // valueOf() 根据名称获取枚举类型
        System.out.println(Day02.valueOf("MONDAY").ordinal());
        
        // 获取所有枚举值
        Day[] days = Day.values();
        // 向上转型
        Enum e = Day.MONDAY;
        Class<?> claz = e.getDeclaringClass();
        if (claz.isEnum()) {
            Day[] dsz = (Day[])claz.getEnumConstants();
            System.out.println("dsz:" + Arrays.toString(dsz));
        }
    }
}
