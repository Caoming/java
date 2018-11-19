package com.felix.chapter7;

public class NotInit {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * static的类变量存储在变量池中，获取static的值，对应类需要进行初始化。执行SupperClass,static代码
         */
        System.out.println(SupperClass.SubClass.value);

        /**
         * 数组在JVM中是一种特殊类型，有对应的指令newarray，所以不会对应的初始化
         */
        SupperClass[] ca = new SupperClass[10];

        /**
         *  final、static 修饰的基本类型和String，不会进行类初始化（Code属性的ConstantValue），
         *  因为在加载的时候此类型的数据已经放在常量池中去了
         */
        System.out.println(ConstClass.HELLO_WORLD);
    }
}
