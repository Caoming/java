package com.felix.prometheus.collector;

import io.prometheus.client.Collector;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author caoming
 * @Date: 2020/7/9 16:07
 */
public class CounterCollector  {
    static Counter counter = Counter.build().name("test_counter").help("test counter")
            .labelNames("caoming","hehehe")
            .register();

   static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5,
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    return thread;
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    static void processThatCalculates () throws Exception{
        counter.labels("caoming", "hehehe").inc();
    }

    public static void main(String[] args) throws Exception {
        processThatCalculates();
        counter.collect();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    processThatCalculates();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },0L,5L,TimeUnit.SECONDS);
        HTTPServer server = new HTTPServer(8686);
    }
}
