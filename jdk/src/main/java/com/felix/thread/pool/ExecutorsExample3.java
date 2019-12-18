package com.felix.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsExample3 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        for(int i = 0; i < 100;i++){
            final int index = i;
            service.execute(()->{
                System.out.println("123123s..."+ index);
            });
        }

        service.shutdown();
    }
}
