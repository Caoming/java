package com.felix.chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Practice {

    public static void main(String[] args) {
        String[] arrayOfWords = {"Hello","Goodbye","World"};

        List<String[]> collect = Arrays.stream(arrayOfWords).map(s -> s.split("")).distinct().collect(toList());
        List<String> flatStream = Arrays.stream(arrayOfWords).map(s -> s.split("")).
                flatMap(Arrays::stream).distinct().collect(toList());


        List<Integer> ints = Arrays.asList(1,2,3,4,5);
        ints.stream().map(i -> i * i).collect(toList());

        // 列表【1，2，3】 和【3，4】 形成对数（1，3），（1，4），（2，3），（2，4），（3，3），（3，4）
        List<Integer> ints1 = Arrays.asList(1,2,3);
        List<Integer> ints2 = Arrays.asList(3,4);
        List<Integer[]> collect1 = ints1.stream().flatMap(i ->
                ints2.stream().map(j -> new Integer[]{i, j}))
                .collect(toList());



        Stream.iterate(new Integer[]{0,1},n -> new Integer[]{n[1],n[0] + n[1]}).limit(20).forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
    }
}
