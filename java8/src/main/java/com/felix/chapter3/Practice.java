package com.felix.chapter3;

import com.felix.common.Apple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 第三章书本上的知识
 */
public class Practice {

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

        // lambda 写法
        list.sort((Apple a,Apple b) -> a.getWeight().compareTo(b.getWeight()));

        // 方法引用的写法
        list.sort(Comparator.comparing(Apple::getWeight));

        Runnable r1 = () -> System.out.println("hello world one");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world two");
            }
        };

        processing(r1);
        processing(r2);

        // 函数接口的定义，可传lambda表达式
        processing(() -> System.out.println("hello world three"));


        processingFile((BufferedReader br) -> br.readLine());

        processingFile((BufferedReader br) -> br.readLine() + br.readLine());
        processingFile(br -> br.readLine());

        Predicate<String> predicate = s -> s.isEmpty();
        filter(Arrays.asList(new String[]{"","sdfdsf","dsfsdfsd"}),predicate);

        // consumer的应用
        foreach(Arrays.asList(new String[]{"","sdfdsf","dsfsdfsd"}),s -> System.out.println(s));

        map(Arrays.asList(new String[]{"","sdfdsf","dsfsdfsd"}),s -> s.length());

        Supplier<Apple> a = Apple::new;
    }

    public static void processing(Runnable r){
        r.run();
    }


    /**
     * 读取文件信息的代码。
     * 1、 如何修改代码让其读取多行
     * @return
     */
    public static String processingFile(){
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String processingFile(BufferedReaderProcessor processor){
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return processor.process(br);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * predicate 的应用
     * @param list
     * @param p
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T t : list){
            if(p.test(t)){
                result.add(t);
            }
        }
        return result;
    }

    /**
     * consumer的应用
     * @param list
     * @param consumer
     * @param <T>
     */
    public  static <T> void foreach(List<T> list, Consumer<T> consumer){
        for(T t : list){
            consumer.accept(t);
        }
    }

    /**
     * function的应用
     * @param list
     * @param f
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> List<R> map(List<T> list, Function<T,R> f){
        List<R> result = new ArrayList<>();
        for(T t: list){
            result.add(f.apply(t));
        }

        return  result;
    }
}
