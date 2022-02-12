package com.ww.enumstudy;

/**
 * @description: 覆盖enum类方法
 * @author wanggw
 * @date 2022年2月12日 上午11:59:49
 */
public enum Day02 {

    MONDAY("a"),
    TUESDAY("b"),
    WEDNESDAY("c"),
    THURSDAY("d"),
    FRIDAY("e"),
    SATURDAY("f"),
    SUNDAY("g");
    
    private String code;
    
    private Day02(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * 覆盖enum类方法
     */
    @Override
    public String toString() {
        return code;
    }
    
    public static void main(String[] args) {
        for (Day02 day : Day02.values()) {
            System.out.println("name: " + day.name() + ", code: " + day.toString());
//            输出结果
//            name: MONDAY, code: a
//            name: TUESDAY, code: b
//            name: WEDNESDAY, code: c
//            name: THURSDAY, code: d
//            name: FRIDAY, code: e
//            name: SATURDAY, code: f
//            name: SUNDAY, code: g

        }
    }
}
