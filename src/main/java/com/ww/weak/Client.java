package com.ww.weak;

import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) {
        Salad salad = new Salad(new Apple("��ԭ��"));
        
        // WeakReference��get()������ȡApple
        System.out.println("Apple: " + salad.get());
        System.gc();
        
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // ���Ϊ�գ�����������
        if (null == salad.get()) {
            System.out.println("clear Apple.");
        }
    }
}
