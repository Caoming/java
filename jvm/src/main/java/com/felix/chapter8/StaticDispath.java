package com.felix.chapter8;

/**
 * 方法的静态分派
 */
public class StaticDispath {
    static abstract class Human{

    }

    static class  Man extends Human{

    }

    static class Woman extends Human{

    }

    public void sayHello(Human guy){
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy){
        System.out.println("hello,gentleman!");
    }

    public void syaHello(Woman guy){
        System.out.println("hello,lady!");
    }

    /**
     * 1、重载是编译期可知的方法调用，意思就是在方法在调用时是通过静态类型来判断最终调用的方法，运行期是不变的
     * 2、静态分派的动作发生在编译期，不是JVM来执行的
     * 3、有些静态分派的时候并不是唯一的，有时候只能通过语言上的规则进行理解
     * @param args
     */
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispath staticDispath = new StaticDispath();
        staticDispath.sayHello(man);
        staticDispath.sayHello(woman);
    }

}
