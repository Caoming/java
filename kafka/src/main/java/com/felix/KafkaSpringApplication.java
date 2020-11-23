package com.felix;

import com.felix.prometheus.collector.GaugeCollector;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class KafkaSpringApplication implements DisposableBean, ApplicationRunner {
    private static ConfigurableApplicationContext ctx;
    @Autowired
    private GaugeCollector gaugeCollector;

    public static void main(String[] args) {
         ctx = SpringApplication.run(KafkaSpringApplication.class, args);
    }

    @Override
    public void destroy() throws Exception {
        // 关闭线程池
        ThreadPoolTaskScheduler scheduler = (ThreadPoolTaskScheduler) ctx.getBean("scheduler");
        scheduler.shutdown();
        if (null != ctx && ctx.isRunning()) {
            ctx.close();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        gaugeCollector.register();
    }
}
