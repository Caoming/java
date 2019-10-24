package com.felix.jcu;

import java.util.concurrent.*;

public class ExecutorsTest {

    public static void main(String[] args) {
        // 创建一个核定线程数大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService.submit()

        // 创建一个无固定大小线程数的线程池
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        // 创建一个有固定大小线程数，且支持定时的线程池
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(10);
//        newScheduledThreadPool.schedule()


        // 线程池，核心线程数、最大线程数、空闲线程数存活时间，时间单位，排队队列，拒绝策略
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 120,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        System.out.println(threadPoolExecutor.getPoolSize() + "....."+ threadPoolExecutor.getActiveCount() + "...."
        + threadPoolExecutor.getMaximumPoolSize() + "...." + threadPoolExecutor.getLargestPoolSize()
        + "...." + threadPoolExecutor.getCorePoolSize());

    }
}
