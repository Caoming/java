package com.felix.chapter2;

import com.felix.common.Apple;

import java.util.ArrayList;

/**
 * 匿名函数的比较
 */
public class MeaningOfThis {

    public final int value = 4;

    public void doIt(){
        int value = 6;
        Runnable run = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        run.run();
    }

    public static void main(String[] args) {
        new MeaningOfThis().doIt();

        /**
         * Lambda 函数的写法，必须要有接口
         */
        FilterApple.filterApples(new ArrayList<>(),(Apple apple) -> "red".equals(apple.getColor()));
    }
}
