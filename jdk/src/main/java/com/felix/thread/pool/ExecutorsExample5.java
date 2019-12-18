package com.felix.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

        for(int i = 0; i < 100;i++){
            final int index = i;
            service.schedule(()->{
                System.out.println("123123s..."+ index);
            },1, TimeUnit.SECONDS);
        }

        service.shutdown();
    }
}
