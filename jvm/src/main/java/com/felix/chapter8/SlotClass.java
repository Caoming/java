package com.felix.chapter8;

public class SlotClass {

    public static void main(String[] args) {
        /**
         * 还是局部变量，且还处于Slot，GCRoot还关联着,没有被回收
         */
        byte[] placeholder = new byte[64*1024*1024];
        System.gc();

        /**
         * 没有被回收
         */
        {
            byte[]  placeholder1 = new byte[64*1024*1024];
        }
        System.gc();

        /**
         * 回收了placeholder1、placeholder2，未回收placeholder
         */
        {
            byte[]  placeholder2 = new byte[64*1024*1024];
        }
        int a = 0;
        System.gc();

        /**
         * 三者都回收了
         */
        placeholder = null;
        System.gc();

        /**
         * 方法的局部变量是没有初始化的概念，所以编译不过去
         */
//        int b;
//        System.out.println(b);
    }
}
