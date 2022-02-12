package com.ww.enumstudy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: EnumMap基本用法
 * @author wanggw
 * @date 2022年2月12日 下午12:26:42
 */
public class EnumMapDemo {

    public static void main(String[] args) {
        List<Clothes> list = new ArrayList<>();
        list.add(new Clothes("C001",Color.BLUE));
        list.add(new Clothes("C002",Color.YELLOW));
        list.add(new Clothes("C003",Color.RED));
        list.add(new Clothes("C004",Color.GREEN));
        list.add(new Clothes("C005",Color.BLUE));
        list.add(new Clothes("C006",Color.BLUE));
        list.add(new Clothes("C007",Color.RED));
        list.add(new Clothes("C008",Color.YELLOW));
        list.add(new Clothes("C009",Color.YELLOW));
        list.add(new Clothes("C010",Color.GREEN));
        
        // 方案1：使用HashMap
        Map<String, Integer> map = new HashMap<>();
        for (Clothes clothes : list) {
            String colorName = clothes.getColor().name();
            Integer count = map.get(colorName);
            if (null != count) {
                map.put(colorName, count + 1);
            } else {
                map.put(colorName, 1);
            }
        }
        
        System.out.println(map.toString());
        System.out.println("---------------");

        // 方案2：使用EnumMap
        Map<Color, Integer> enumMap = new EnumMap<>(Color.class);
        for (Clothes clothes:list){
            Color color=clothes.getColor();
            Integer count = enumMap.get(color);
            if(count!=null){
                enumMap.put(color,count+1);
            }else {
                enumMap.put(color,1);
            }
        }
        System.out.println(enumMap.toString());
        
        /**
         * 虽然使用其他的Map也能完成相同的功能，但是使用EnumMap会更加高效，它只能接收同一枚举类型的实例作为键值，且不能为null，
         * 由于枚举类型实例的数量相对固定并且有限，所以EnumMap使用数组来存放与枚举类型对应的值，毕竟数组是一段连续的内存空间，根据
         * 程序局部性原理，效率会相当高效。
         */
    }
}

class Clothes {
    
    private String code;
    
    private Color color;
    
    public Clothes() {}
    
    public Clothes(String code, Color color) {
        this.code = code;
        this.color = color;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}