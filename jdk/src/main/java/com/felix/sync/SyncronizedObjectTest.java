package com.felix.sync;


/**
 * synchronized 三种用法
 * * 1、类对象使用
 * * 2、实例对象使用
 * * 3、方法块的使用
 */
public class SyncronizedObjectTest implements Runnable{
    static  int i = 0;

    /**
     * 锁定的是调用的实例对象
     */
    public synchronized void add(){
        i++;
    }

    @Override
    public void run() {
        for(int j = 0; j < 10000; j++){
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncronizedObjectTest test1 = new SyncronizedObjectTest();
        // 同步锁的是调用的test1 这个实例对象
        Thread thread1 = new Thread(test1);
        Thread thread2 = new Thread(test1);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(i);

        i = 0;
        SyncronizedObjectTest test2 = new SyncronizedObjectTest();
        // 同步锁的是调用的test1、test2 这个实例对象，没有占用资源，是线程不安全的
        Thread thread3 = new Thread(test1);
        Thread thread4 = new Thread(test2);

        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
        System.out.println(i);

    }
}
