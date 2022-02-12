package com.ww.enumstudy;

/**
 * @description: 枚举与switch
 * @author wanggw
 * @date 2022年2月12日 下午12:16:48
 */
public class EnumDemo04 {

    /**
     * 使用switch进行条件判断
     */
    public static void printName(Color color) {
        // 无需使用Color进行引用
        switch (color) {
            case BLUE :
                System.out.println("蓝色");
                break;
            case RED:
                System.out.println("红色");
                break;
            case GREEN:
                System.out.println("绿色");
                break;
        }
    }
    
    public static void main(String[] args) {
        printName(Color.BLUE);
        printName(Color.RED);
        printName(Color.GREEN);
    }
}

enum Color {
    GREEN,
    RED,
    BLUE,
    YELLOW
}