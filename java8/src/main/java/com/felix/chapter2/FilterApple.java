package com.felix.chapter2;

import com.felix.common.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class FilterApple {
    /**
     * 过滤绿色苹果
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> resultList = new ArrayList<>();
        for(Apple apple : inventory){
            if("green".equals(apple.getColor())){
                resultList.add(apple);
            }
        }

        return resultList;
    }

    /**
     * 过滤指定颜色的苹果
     * @param inventory
     * @return
     */
    public static List<Apple> filterColorApples(List<Apple> inventory,String color){
        List<Apple> resultList = new ArrayList<>();
        for(Apple apple : inventory){
            if(color.equals(apple.getColor())){
                resultList.add(apple);
            }
        }

        return resultList;
    }

    /**
     * 根据重量来过滤苹果
     * @param inventory
     * @return
     */
    public static List<Apple> filterWeightApples(List<Apple> inventory, int weight){
        List<Apple> resultList = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.getWeight() > weight){
                resultList.add(apple);
            }
        }

        return resultList;
    }

    // 如果需要即要过滤颜色，又要过滤重量如何处理
    /**
     * 利用行为参数化 @see ApplePredicate
     */

    public static List<Apple> filterApples(List<Apple> inventory,ApplePredicate applePredicate){
        List<Apple> resultList = new ArrayList<>();
        for(Apple apple : inventory){
            // 此段采用了行为参数化，利用策略模式。把代码利用策略模式传一个对象进行，以便来执行代码
            /**
             * 此种方式，有个缺点：过于复杂，需要通过对象来传递需要执行的代码
             */
            if(applePredicate.test(apple)){
                resultList.add(apple);
            }
        }

        return resultList;
    }



    public static List<Apple> anonymousFilterApples(){
        List<Apple> inventory = new ArrayList<>();

        /**
         * 利用匿名函数，过于复杂。详见 @see MeaningOfThis
         */
        filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return false;
            }
        });
        return inventory;
    }

    /**
     * 利用lambda函数写过滤的方法方式
     * @param list
     * @param p
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e : list){
            if(p.test(e)){
                result.add(e);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // 对苹果按照重量进行排序的写法
        List<Apple> list = new ArrayList<>();

        // 匿名函数的写法
        list.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        // lambda表达式的写法
        list.sort((Apple o1,Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));

        // 方法饮用的lambda写法
        list.sort(Comparator.comparing(Apple::getWeight));

    }
}
