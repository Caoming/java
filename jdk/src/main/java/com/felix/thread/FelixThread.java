package com.felix.thread;

public class FelixThread {

    private static class TestThread extends Thread{
        public void run(){
            for(int i = 0; i < 100000; i++){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("被中断了，赶紧结束");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestThread testThread = new TestThread();
        testThread.setName("张三");
        TestThread testThread1 = new TestThread();
        testThread1.setName("李四");

        testThread.start();
        // 等待线程执行完再执行下面的步骤
        Thread.sleep(300);
        // 当前线程让出CPU时间片
        Thread.yield();
        // 请求中断线程
//        testThread.interrupt();
        // 当前线程暂停执行，等待子线程完成才执行主线程
//        testThread.join();
        testThread1.start();
        testThread1.wait();
        testThread1.notify();
    }
}
