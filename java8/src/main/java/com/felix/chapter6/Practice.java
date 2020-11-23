package com.felix.chapter6;

import com.felix.common.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Practice {

    public static void main(String[] args) {
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish("1",true,1, Dish.Type.FISH));
        menu.add(new Dish("2",false,2, Dish.Type.MEAT));
        menu.add(new Dish("3",true,3, Dish.Type.OTHER));
        menu.add(new Dish("4",false,4, Dish.Type.MEAT));

        // 总数
        menu.stream().collect(Collectors.counting());
        menu.stream().count();

        // 归约求最大的热量的菜collect  reducing的方式
        Optional<Dish> dish = menu.stream().
                collect(Collectors.reducing((m1, m2) -> m1.getCalories() > m2.getCalories() ? m1 : m2));

        // 分组
        Map<Dish.Type, List<Dish>> collect = menu.stream().collect(Collectors.groupingBy(Dish::getType));

//        // 多级分组
//        Map<Dish.Type, Map<String, List<Dish>>> map = menu.stream().collect(
//                Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
//            if (dish.getCalories() < 3) {
//                return "normal";
//            } else {
//                return "FAT";
//            }
//        })));

        // 多级分组
        Map<Dish.Type, Long> typeLongMap = menu.stream().
                collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        // 分区，是否为蔬菜分区
        Map<Boolean, List<Dish>> booleanListMap = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));

        // 分区后进行分组
        Map<Boolean, Map<Dish.Type, List<Dish>>> mapMap = menu.stream().
                collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
    }


    /**
     * 是否为质数的方法
     * @param candidate
     * @return
     */
    public static  boolean  isPrime(int candidate){
        return IntStream.range(2,candidate).
                noneMatch(i -> candidate % i == 0);
    }

    /**
     * 质数和非质数进行区分
     * @param n
     * @return
     */
    public static Map<Boolean,List<Integer>> partitionPrimes(int n){
        return IntStream.range(2,n).boxed().collect(Collectors.partitioningBy(i -> isPrime(i)));
    }
}
