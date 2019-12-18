package com.felix.thread.lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LockBlockingQueueTest {

    public static void main(String[] args) throws Exception{
        LinkedBlockingQueue<Integer> queues = new LinkedBlockingQueue<>(100);
        ThreadPoolExecutor producerExecutors = new ThreadPoolExecutor(5, 100, 0l,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000), (r, e) -> {
            System.out.println("拒绝了");
        });

        for(Integer i = 0; i < 1000;i++){
            final int index = i;
            producerExecutors.submit(()->{
                try{
                    Thread.sleep(10l);
                    System.out.println("生产者。。。"+queues.offer(index));
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
                    Thread.sleep(40l);
                    System.out.println("消费者..."+queues.take());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }

        while (true) {
            Thread.sleep(5000l);
//            System.out.println("生产者生产了。。" + queues.);
//            System.out.println("消费者消费了。。" + lockTest.consumerSize);
            if (producerExecutors.getQueue().size() == 0 && consumerExecutors.getQueue().size() == 0) {
                producerExecutors.shutdown();
                consumerExecutors.shutdown();
                break;
            }
        }
    }
}
