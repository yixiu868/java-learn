package com.ww.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: 通过反射实现copy功能
 * @author xiaohua
 * @date 2021年8月5日 上午10:14:04
 */
public class ReflectCopyUtils {

    @SuppressWarnings("unchecked")
    public <T> T copy(T source) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 获得对象的类型
        Class<T> classType = (Class<T>) source.getClass();
        System.out.println("Class类型：" + classType.getName());
        
        // 使用默认构造方法构建一个目标对象
        T target = (T) classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
        
        Field[] fields = classType.getDeclaredFields();
        System.out.println("copy工具请开始你的表演...");
        for (Field field : fields) {
            // 获取字段名称
            String fieldName = field.getName();
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            
            // 构建set、get方法名称
            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            String setMethodName = "set" + firstLetter + fieldName.substring(1);
            
            // 获取方法名对应method
            Method getMethod = classType.getMethod(getMethodName, new Class[] {});
            Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });
            
            // 调用源对象的get方法
            Object value = getMethod.invoke(source, new Object[] {});
            // 调用目标对象的set方法
            setMethod.invoke(target, new Object[] { value });
        }
        
        System.out.println("表演圆满完成!");
        return target;
    }
}
