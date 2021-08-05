package com.ww.jvm.load;

/**
 * @Description: 验证父类clinit在子类的clinit之前执行
 * @author xiaohua
 * @date 2021年8月5日 下午3:45:43
 */
public class ClinitDemo {

    static class Parent {

        public static int A = 1;
        
        static {
            A = 2;
        }
    }
    
    static class Sub extends Parent {
        
        public static int B = A;
    }
    
    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}