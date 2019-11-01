package com.felix.agent;

public class TimerApp {

    public static void main(String[] args) {
        while (true){
            System.out.println(getGreeting());
        }
    }

    public static String getGreeting(){
        try {
            Thread.sleep((long) (1000*Math.random()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "hhhhhhh";
    }
}
