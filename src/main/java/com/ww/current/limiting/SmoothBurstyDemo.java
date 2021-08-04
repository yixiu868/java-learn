package com.ww.current.limiting;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author wanggw
 * @description TODO
 * @date 2020/5/28 21:57
 */
public class SmoothBurstyDemo {

    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire(10));
        System.out.println(limiter.acquire());

        /*System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());

        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());*/
    }
}
