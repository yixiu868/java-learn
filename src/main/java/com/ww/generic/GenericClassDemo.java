package com.ww.generic;

/**
 * 泛型类
 *
 * @author wanggw
 * @date 2022-01-29 23:11:35
 */
public class GenericClassDemo<T> {
    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    private T a;

    public GenericClassDemo(T a) {
        this.a = a;
    }

    public static void main(String[] args) {
        GenericClassDemo<Demo> demo = new GenericClassDemo<>(new Demo());
        
    }
}

class Demo {

}