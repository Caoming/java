package com.felix.chapter4;

import com.felix.common.Dish;
import com.felix.common.OrderLog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingInt;

public class Practice {

    public static void main(String[] args) {
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish("1",true,1, Dish.Type.FISH));
        menu.add(new Dish("2",false,2, Dish.Type.MEAT));
        menu.add(new Dish("3",true,3, Dish.Type.OTHER));
        menu.add(new Dish("4",false,4, Dish.Type.MEAT));


        //  流过程执行的状态
        List<Integer> collect = menu.stream().filter(d -> {
            System.out.println("filter...." + d.getName());
            return d.isVegetarian();
        }).map(d -> {
            System.out.println("map ....." + d.getName());
            return d.getCalories();
        }).collect(Collectors.toList());

        // 多线程的
        List<Integer> collects = menu.parallelStream().filter(d -> {
            System.out.println("filter...." + d.getName());
            return d.isVegetarian();
        }).map(d -> {
            System.out.println("map ....." + d.getName());
            return d.getCalories();
        }).collect(Collectors.toList());

        // distinct 处理
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 1, 23, 2, 2, 3);
        integers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

        // 截短流
        integers.stream().filter(i -> i % 2 == 1).limit(3).forEach(System.out::println);
        integers.stream().filter(i -> i % 2 == 1).skip(3).forEach(System.out::println);


        // 流的偏平化
        String[] words = new String[]{"hello","world"};
        // 第一个map，分为两个String[]的流，flatMap把两个String[]流合并成一个String[]的流
        List<String> distinctFlatMap = Arrays.stream(words).map(w -> w.split("")).
                flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        /**
         *  给定两个数字列表，如何返回所有的数对呢?例如，给定列表[1, 2, 3]和列表[3, 4]，
         *  应 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
         *  为简单起见，你可以用有两个元素的数组来代 表数对
         */
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Integer[]> collect1 = numbers1.stream().
                flatMap(i -> numbers2.stream().
                        map(j -> new Integer[]{i, j})).
                collect(Collectors.toList());
        System.out.println("................");

        /**
         * anyMatch 其中有一个满足就可以
         * allMatch 都能匹配条件
         * noneMatch 没有一个能满足
         */
        boolean anyMatch = menu.stream().anyMatch(Dish::isVegetarian);
        boolean allMatch = menu.stream().allMatch(d -> d.getCalories() < 1000);
        boolean noneMatch = menu.stream().noneMatch(dish -> dish.getCalories() > 1000);

        /**
         * findAny 返回任意一个满足的条件的值，Optional是否存在的值
         * 在并行中findAny 比findFirst约束更少
         */
        Optional<Dish> any = menu.stream().filter(dish -> dish.getCalories() > 200).findAny();
        // ifPresent 存在输出菜名
         menu.stream().filter(dish -> dish.getCalories() > 200).findAny().ifPresent(dish -> System.out.println(dish.getName()));

        /**
         * 归约的概念
         */
        List<Integer> reduceIntegers = Arrays.asList(1, 23, 342, 2342, 2342, 2342, 42);

        // 求和 map-reduce的方式
        Integer reduce = reduceIntegers.stream().map(dish -> dish).reduce(0, (a, b) -> a + b);
        System.out.println(reduce);

        // 最大值
        reduceIntegers.stream().reduce(Integer::max).ifPresent(max -> System.out.println(max));
        reduceIntegers.stream().max(Integer::compare).ifPresent(max -> System.out.println(max));

        // 最小值
        reduceIntegers.stream().reduce(Integer::min).ifPresent(min -> System.out.println(min));
        reduceIntegers.stream().min(Integer::compare).ifPresent(min -> System.out.println(min));

        System.out.println("练习第五章使用流。。。。。。。。。。。。。。。。");
        List<OrderLog> logs = new ArrayList<>();
        logs.add(new OrderLog(2011,200.00d,"SH","caoming"));
        logs.add(new OrderLog(2012,300.00d,"BJ","caoming2"));
        logs.add(new OrderLog(2013,400.00d,"SH","caoming2"));
        logs.add(new OrderLog(2018,2600.00d,"BJ","caoming1"));
        logs.add(new OrderLog(2019,2200.00d,"SH","caoming4"));
        logs.add(new OrderLog(2021,2100.00d,"SZ","caoming3"));
        logs.add(new OrderLog(2011,20.00d,"SH","caoming5"));
        logs.add(new OrderLog(2013,21200.00d,"GZ","caoming7"));

        logs.stream().filter(l -> Arrays.stream(new Integer[]{2011,2012}).anyMatch(i ->l.getYear() == i)).collect(Collectors.toList());

        // 找出2011年发生的所有交易，并按照交易额排序
        logs.stream().filter(s -> 2011 == s.getYear()).
                map(s -> s.getAccount()).sorted(Double::compare)
                .forEach(s -> System.out.println(s));

        // 交易员都在哪些地方工作过
        logs.stream().map(s -> s.getCity()).distinct().forEach(s -> System.out.println(s));

        // 查找所有来自于上海的工作人员，按姓名排序
        logs.stream().filter(s -> "SH".equals(s.getCity())).
                map(s -> s.getChangeName()).sorted(String::compareTo).
                forEach(s -> System.out.println(s));

        // 有木有工作人员在深圳工作
        logs.stream().filter(s -> "SZ".equals(s.getCity())).
                map(s -> s.getChangeName()).findAny().
                ifPresent(s -> System.out.println(s));

        // 在上海人员的所有交易额
        logs.stream().filter(s -> "SH".equals(s.getCity())).
                map(s -> s.getAccount()).forEach(s -> System.out.println(s));

        // 最大交易额
        logs.stream().map(s -> s.getAccount()).reduce(Double::max).ifPresent(s -> System.out.println(s));

        // 最小交易额
        logs.stream().map(s -> s.getAccount()).reduce(Double::min).ifPresent(s -> System.out.println(s));
        
        // 元素和，原始类型装箱
        Integer sum = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);

        // 利用原始类型流
        int sumInt = menu.stream().mapToInt(Dish::getCalories).sum();

        // 原始类型流转为普通流
        Stream<Integer> boxed = menu.stream().mapToInt(Dish::getCalories).boxed();

        // Optional 值
        OptionalInt optionalInt = menu.stream().mapToInt(Dish::getCalories).max();
        // 如果存在，返回存在的值，不存在返回1
        optionalInt.orElse(1);

        // 原始值的数值流,rang 左闭右开, rangClosed 左闭右闭
        IntStream.range(0,100).filter(n -> n % 2 == 0).forEach(s -> System.out.println(s));
        IntStream.rangeClosed(0,100).filter(n -> n % 2 == 0).forEach(s -> System.out.println(s));

        // 勾股数
        IntStream.rangeClosed(0,100).boxed().
                flatMap(a ->
                        IntStream.rangeClosed(a,100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).
                                mapToObj( b -> new int[]{a,b,(int) Math.sqrt(a * a + b * b)}))
        .forEach(t -> System.out.println(t[0] + "..." + t[1] + "..." + t[2]));

        // 勾股数优化
        IntStream.rangeClosed(0,100).boxed().flatMap(
                a -> IntStream.rangeClosed(a,100).mapToObj(
                        b -> new double[]{a,b,Math.sqrt(a * a + b * b)}
                ).filter(t -> t[2] % 1 == 0)).forEach(
                t -> System.out.println(t[0] + "..." + t[1] + "..." + t[2]));

        /**
         * 由值创建流
         */
        Stream<String> stringStream = Stream.of("123", "1231", "java", "flink");

        // 数值创建流
        int[] numbers = new int[]{1,23,23,123,1231,312,3123,1312,3123};
        Arrays.stream(numbers);

        // 由文件生成流，文件里总共有多少不同的单词
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
            lines.flatMap(line -> Arrays.stream(line.split( " "))).distinct().count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建无限流
        Stream.iterate(0, n -> n +2).limit(10).forEach(s -> System.out.println(s));

        // 斐波纳契数列
        Stream.iterate(new int[]{0,1} , a -> new int[]{a[1],a[0] + a[1]} ).limit(20).forEach(t -> System.out.println(t[0] + "..." + t[1]));

        // 根据城市分组
        Map<String, List<OrderLog>> stringListMap = logs.stream().collect(groupingBy(OrderLog::getCity));

        IntSummaryStatistics summaryStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics.toString());
    }
}
