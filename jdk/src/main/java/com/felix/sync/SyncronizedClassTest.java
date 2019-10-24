package com.felix.sync;

public class SyncronizedClassTest implements Runnable {
    private static int i = 0;

    public static synchronized void add(){
        i++;
    }

    /**
     * 调用对象的锁
     */
    public  void addBlock(){
        synchronized (this){
            i++;
        }
    }

    /**
     * 类对象的锁
     */
    public void addBlockStatic(){
        synchronized (SyncronizedClassTest.class){
            i++;
        }
    }

    @Override
    public void run() {
        for(int j = 0; j < 10000;j++) {
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncronizedClassTest test1 = new SyncronizedClassTest();
        SyncronizedClassTest test2 = new SyncronizedClassTest();
        // 同步锁的是SyncronizedClassTest 这个类对象
        Thread thread1 = new Thread(test1);
        Thread thread2 = new Thread(test2);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(i);
    }
}
