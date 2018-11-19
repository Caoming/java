package com.felix.chapter7;

public class SupperClass {
    static{
        System.out.println("SupperClass init.");
    }

    public static int value = 123;

    public static class SubClass extends SupperClass{
        static {
            System.out.println("SubClass init.");
        }

    }
}
