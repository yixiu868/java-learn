package com.ww.weak;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class Client2 {

    public static void main(String[] args) {
        ReferenceQueue<Apple> appleReferenceQueue = new ReferenceQueue<>();
        WeakReference<Apple> appleReference = new WeakReference<Apple>(new Apple("��ƻ����԰"), appleReferenceQueue);
        WeakReference<Apple> appleReference2 = new WeakReference<Apple>(new Apple("��ƻ��"), appleReferenceQueue);
        
        System.out.println("============gc����ǰ=============");
        Reference<? extends Apple> reference = null;
        while (null != (reference = appleReferenceQueue.poll())) {
            System.out.println(reference); // �������ޣ��޷����
        }
        System.out.println(appleReference);
        System.out.println(appleReference2);
        System.out.println(appleReference.get());
        System.out.println(appleReference2.get());
        
        System.out.println("============gc����=============");
        System.gc();
        
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("============gc���ú�=============");
        // �����ѱ�����
        System.out.println(appleReference.get());
        System.out.println(appleReference2.get());
        
        Reference<? extends Apple> reference2 = null;
        while (null != (reference2 = appleReferenceQueue.poll())) {
            System.out.println("appleReferenceQueue����:" + reference2);
        }
    }
}
