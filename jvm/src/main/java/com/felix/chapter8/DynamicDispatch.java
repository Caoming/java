package com.felix.chapter8;

/**
 * 动态分配，重写
 */
public class DynamicDispatch {
    static abstract class  Human{
        protected abstract void sayHello();
    }

    static class  Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("say hello man!");
        }
    }

    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("say hello woman!");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
