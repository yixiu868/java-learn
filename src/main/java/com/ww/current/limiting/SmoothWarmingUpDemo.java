package com.ww.current.limiting;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author wanggw
 * @description TODO
 * @date 2020/5/28 22:14
 */
public class SmoothWarmingUpDemo {

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);
        for (int i = 1; i < 5; i++) {
            System.out.println(limiter.acquire());
        }

        Thread.sleep(1000L);

        for (int i = 1; i < 5; i++) {
            System.out.println(limiter.acquire());
        }
    }
}
