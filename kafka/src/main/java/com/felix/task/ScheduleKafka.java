package com.felix.task;

import com.felix.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleKafka {
    @Autowired
    private Producer producer;

//    @Scheduled(cron = "0 0/1 * * * ?")
    public void task() throws Exception {
        producer.send(null);
    }

}
