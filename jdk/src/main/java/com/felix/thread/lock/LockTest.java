package com.felix.thread.lock;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    private Object[] datas = new Object[100];

    // 容量中的数据
    private AtomicInteger count = new AtomicInteger(0);

    // 生产的总量
    private int producerSize = 0;
    // 消费的总量
    private int consumerSize = 0;

    /**
     * 模拟消息队列生产者和消费者
     */
    public  void put(Object o){
        lock.lock();
        try{
            if(count.get() == 100){
                System.out.println("队列已满");
                notFull.await();
            }
            System.out.println("数据插入成功～～～");
            producerSize++;
            notEmpty.signal();

            datas[count.get()] = o;
            count.incrementAndGet();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("生产者解锁了..");
            lock.unlock();
        }
    }

    public Object take(){
        lock.lock();
        try{
            if(count.get() == 0){
                System.out.println("队列已空");
                notEmpty.await();
            }
            int i = count.decrementAndGet();
            Object o = datas[i];
            datas[i] = null;
            consumerSize++;
            System.out.println("消费到了～～～");
            notFull.signal();
            return o;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("消费者解锁了...");
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        LockTest lockTest = new LockTest();
        ThreadPoolExecutor producerExecutors = new ThreadPoolExecutor(5, 100, 0l,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000), (r, e) -> {
            System.out.println("拒绝了");
        });

        for(Integer i = 0; i < 1000;i++){
            final int index = i;
            producerExecutors.submit(()->{
               try{
                   Thread.sleep(10l);
                   lockTest.put(index);
               }catch (Exception e){
                    e.printStackTrace();
               }
            });
        }


        ThreadPoolExecutor consumerExecutors = new ThreadPoolExecutor(5, 100, 0l,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000), (r, e) -> {
            System.out.println("消费者拒绝了");
        });

        for(Integer i = 0; i < 1000;i++){
            consumerExecutors.submit(()->{
                try{
                    Thread.sleep(50l);
                    lockTest.take();
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }

        while (true) {
            Thread.sleep(5000l);
            System.out.println("生产者生产了。。" + lockTest.producerSize);
            System.out.println("消费者消费了。。" + lockTest.consumerSize);
            if (producerExecutors.getQueue().size() == 0 && consumerExecutors.getQueue().size() == 0) {
                producerExecutors.shutdown();
                consumerExecutors.shutdown();
                break;
            }
        }
    }

}
