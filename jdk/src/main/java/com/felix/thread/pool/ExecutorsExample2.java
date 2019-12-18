package com.felix.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsExample2 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 100;i++){
            final int index = i;
            service.execute(()->{
                System.out.println("123123s..."+ index);
            });
        }

        service.shutdown();
    }
}
