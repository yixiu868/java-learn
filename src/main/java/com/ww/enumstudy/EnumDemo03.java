package com.ww.enumstudy;

/**
 * @description: enum类中定义抽象方法，使每个枚举实例实现该方法，以便产生不同的行为方式
 * @author wanggw
 * @date 2022年2月12日 下午12:11:16
 */
public enum EnumDemo03 {

    FIRST {
        @Override
        public String getInfo() {
            return "FIRST TIME";
        }
    },
    SECOND {
        @Override
        public String getInfo() {
            return "SECOND TIME";
        }
    };
    
    
    /**
     * @description: 定义抽象方法
     * @author wanggw
     * @return
     * @return String
     */
    public abstract String getInfo();
    
    public static void main(String[] args) {
        System.out.println("F:" + EnumDemo03.FIRST.getInfo());
        System.out.println("S:" + EnumDemo03.SECOND.getInfo());
//        输出结果
//        F:FIRST TIME
//        S:SECOND TIME
    }
}
