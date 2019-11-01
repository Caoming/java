package com.felix.agent;

public class MyProgram {

    public static void main(String[] args) throws Exception{
        System.out.println("主程序要执行了！");
        while (true){
            getGreeting();
            Thread.sleep(1000);
        }
    }

    public static void getGreeting(){
        System.out.println("hello world");
    }
}
