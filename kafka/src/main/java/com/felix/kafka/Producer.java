package com.felix.kafka;

import com.felix.vo.TestVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.*;
import java.net.URL;
import java.util.stream.Stream;

@Component
public class Producer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private static Gson gson = new GsonBuilder().create();

    public static void main(String[] args) throws Exception {
        String filePath = Producer.class.getClassLoader().getResource("notification_sm_message_log.binlog.txt").getFile();
        InputStream input = new FileInputStream(filePath);
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = input.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        String string = out.toString();
        String[] split = string.split("\n");
        Stream.of(split).forEach(s ->{
            System.out.println("............................");
            System.out.println(s);
        });

    }

    public void send(TestVo testVo) throws Exception {
        InputStream filePath = Thread.currentThread().getContextClassLoader().getResourceAsStream("notification_sm_message_log.binlog.txt");

        sendKafkaMsg(filePath);
        InputStream  noMessagePath =  Thread.currentThread().getContextClassLoader().getResourceAsStream("not_message.log");
        sendKafkaMsg(noMessagePath);
    }

    private void sendKafkaMsg(InputStream input) throws IOException {
//        InputStream input = new FileInputStream(filePath);
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = input.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        String string = out.toString();

        String[] values = string.split("\n");

        System.out.println(values.length + "........................................");
        for(String v :values) {
            logger.info("发送的数据为：{}",v);
                ListenableFuture future = kafkaTemplate.send("hh_sms_monitor", 8, null, v);
            future.addCallback(new MyListenableFutureCallback());
        }
    }

    class MyListenableFutureCallback implements ListenableFutureCallback<TestVo> {

        @Override
        public void onFailure(Throwable throwable) {
            logger.error(throwable.getLocalizedMessage());
        }

        @Override
        public void onSuccess(@Nullable TestVo o) {
            logger.info("send lsuccess, data is {}", gson.toJson(o));
        }
    }
}
