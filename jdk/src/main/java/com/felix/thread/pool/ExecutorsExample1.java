package com.felix.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsExample1 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        for(int i = 0; i < 10;i++){
            final int index = i;
            service.execute(()->{
                System.out.println("123123s..."+ index);
            });
        }

        service.shutdown();
    }
}
