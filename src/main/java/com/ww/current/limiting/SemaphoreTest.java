package com.ww.current.limiting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author wanggw
 * @description TODO
 * @date 2020/5/28 15:39
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            final int no = i;
            executorService.execute(() -> {
               try {
                   semaphore.acquire();
                   System.out.println("获取信号者: " + no);
                   Thread.sleep((long) (Math.random() * 10000));
                   semaphore.release();
                   System.out.println("可用信号量**" + semaphore.availablePermits());
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            });
        }
        executorService.shutdown();
    }
}
