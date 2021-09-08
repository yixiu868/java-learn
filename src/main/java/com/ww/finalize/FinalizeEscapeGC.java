/**
 * 
 */
package com.ww.finalize;

/**
 * @version 1.0
 * @description TODO
 * @author wanggw
 * @date 2020-05-01 07:01:05
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;
    
    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }
    
    @SuppressWarnings("deprecation")
	@Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }
    
    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        
        // 对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法优先级低, 所以暂停0.5秒
        Thread.sleep(500);
        if (null != SAVE_HOOK) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }
        
        // 下面这段代码与上面的完全相同, 但是这次不会再自救了
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (null != SAVE_HOOK) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }
    }
}
