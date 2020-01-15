package com.felix.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Map<Integer,Integer> map = new HashMap<>();
        Random random = new Random();


        for(int i = 0; i < (1<<15);i++) {
            executorService.execute(() -> {
                map.put(random.nextInt(), random.nextInt());
            });
        }

        System.out.println(map.size());
    }
}
