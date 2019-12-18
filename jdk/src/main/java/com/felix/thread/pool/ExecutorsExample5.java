package com.felix.thread.pool;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

public class ExecutorsExample5 {


    /**
     * 测试： 提交15个执行时间需要3秒的任务,看线程池的状况
     *
     */
    public void executor(ThreadPoolExecutor poolExecutor) throws Exception{
        for(int i = 0; i < 15; i++){
            final int index = i;
            poolExecutor.submit(()->{
                System.out.println("开始执行" + index);
                try{
                    Thread.sleep(3000L);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("执行完毕" + index);
            });
        }
        System.out.println("任务提交成功");

        Thread.sleep(500l);
        System.out.println("线程池中，当前线程数为："+poolExecutor.getPoolSize());
        System.out.println("线程池中，核心线程数为："+poolExecutor.getCorePoolSize());
        System.out.println("线程池中，活动线程数为："+poolExecutor.getActiveCount());
        System.out.println("线程池中，等待任务数为："+poolExecutor.getQueue().size());

        Thread.sleep(15000L);
        System.out.println("线程池中，当前线程数为："+poolExecutor.getPoolSize());
        System.out.println("线程池中，核心线程数为："+poolExecutor.getCorePoolSize());
        System.out.println("线程池中，活动线程数为："+poolExecutor.getActiveCount());
        System.out.println("线程池中，等待任务数为："+poolExecutor.getQueue().size());
    }

    @Test
    public void testPool1() throws Exception{
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                10,5l,TimeUnit.SECONDS,new LinkedBlockingQueue<>());

        executor(poolExecutor);
        poolExecutor.shutdown();
    }

    @Test
    public void testPool2() throws Exception{
        // 队列最多3个任务等待
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                10,5l,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),(r,executor) ->{
            System.out.println("你被拒绝了...");
        });

        executor(poolExecutor);
        /**
         * 预计结果
         * 1、核心线程开启5个，核心线程都被执行的时候，还有任务，继续开启5个线程，
         * 2、最大线程10个都在执行时，任务加入队列，队列满3个后，还有任务直接丢弃
         * 3、丢弃策略
         *  a、直接丢弃：DiscardPolicy
         *  b、丢弃并报错：AbortPolicy
         *  c、重新执行，重新执行的线程是当前线程池的执行：CallerRunsPolicy
         *  d、把队列中等待最长的任务进行剔除，然后把拒绝的任务插入：DiscardOldestPolicy
         *
         */
    }

    @Test
    public void testPool3() throws Exception{
        // 直接丢弃
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                10,5l,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        executor(poolExecutor);
    }

    @Test
    public void testPool4() throws Exception{
        // 抛出异常
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                10,5l,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),
                new ThreadPoolExecutor.AbortPolicy()
        );

        executor(poolExecutor);
    }

    @Test
    public void testPool5() throws Exception{
        // 丢弃的任务当前线程执行
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                10,5l,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        executor(poolExecutor);
    }

    @Test
    public void testPool6() throws Exception{
        // 丢弃的任务重新插入队列，删除等待最久的队列
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                10,5l,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        executor(poolExecutor);
    }
    @Test
    public void testPool8() throws Exception{
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0,
                Integer.MAX_VALUE,5l,
                TimeUnit.SECONDS,new SynchronousQueue<>(),
                (r,e)->{ System.out.println("被拒绝了"); }
        );

        executor(poolExecutor);
    }

    @Test
    public void testPool9() throws Exception{
        // 定时器
        ScheduledThreadPoolExecutor executor = new
                ScheduledThreadPoolExecutor(2,
                (r,e)->{
                    System.out.println("被拒绝了....");
                });

        for(int i = 0; i < 15; i++){
            final int index = i;
            executor.scheduleAtFixedRate(()->{
                try {
                    Thread.sleep(10000l);
                    System.out.println("任务执行了:" + index);
                }catch (Exception e){
                    e.printStackTrace();
                }
            },0l,2000,TimeUnit.MILLISECONDS);
//            executor.scheduleWithFixedDelay(()->{
//                try {
//                    Thread.sleep(10000l);
//                    System.out.println("任务执行了:" + index);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            },0l,2000,TimeUnit.MILLISECONDS);
        }



        while (true) {
            System.out.println("线程池中，当前线程数为：" + executor.getPoolSize());
            System.out.println("线程池中，核心线程数为：" + executor.getCorePoolSize());
            System.out.println("线程池中，活动线程数为：" + executor.getActiveCount());
            System.out.println("线程池中，等待任务数为：" + executor.getQueue().size());
            Thread.sleep(4000l);
            if(executor.getPoolSize() == 0){
                break;
            }
        }

    }

    /**
     * SynchronousQueue
     * @throws Exception
     */
    @Test
    public void testPool7() throws Exception{
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        for(int i =0; i < 10; i++){
            new Thread(new ThreadQueueProducer(queue)).start();
        }

        for(int i =0; i < 10; i++){
            new Thread(new ThreadQueueConsumer(queue)).start();
        }
    }

    class ThreadQueueProducer implements Runnable{
        BlockingQueue queue;
        int cnt =0 ;

        ThreadQueueProducer(BlockingQueue queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            String name  ="";
            int val =0;
            Random random = new Random(System.currentTimeMillis());
            while (true){
                cnt = (cnt + 1) & 0xFFFFFFFF;
                try {
                    val = random.nextInt() % 15;
                    if(val < 5){
                        name = "offer name:" + cnt;
                        queue.offer(name);
                    }else if(val < 10){
                        name = "put name:" +cnt;
                        queue.put(name);
                    }else {
                        name = "offer wait time and name:" + cnt;
                        queue.offer(name,1,TimeUnit.SECONDS);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    class ThreadQueueConsumer implements Runnable{
        BlockingQueue queue;
        ThreadQueueConsumer(BlockingQueue queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Object take = queue.take();
                    System.out.println("task:" + take.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
