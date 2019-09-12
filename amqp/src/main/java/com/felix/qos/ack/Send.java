package com.felix.qos.ack;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 持久化数据处理
 * @author felix
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection collection = RabbitCollectionUtils.getCollection();
        Channel channel = collection.createChannel();

        channel.exchangeDeclare(RabbitCollectionUtils.FELIX_DURATION_TEST,"direct",true);

        Map<String, Object> argsMap = new HashMap<String, Object>();
        argsMap.put("x-dead-letter-exchange", "felix.exchange.name");
        argsMap.put("x-dead-letter-routing-key", "felix.routing.key");

        channel.queueDeclare("felix_duration_test",true,false,false,argsMap);
        channel.queueBind("felix_duration_test","felix_duration_test","felix_duration_test",argsMap);

        for(int i = 0; i < 1000000; i++) {
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("60000").build();
            channel.basicPublish(RabbitCollectionUtils.FELIX_DURATION_TEST, RabbitCollectionUtils.FELIX_DURATION_TEST, properties, ("hello, i am simple rabbitmq"+i).getBytes());
            System.out.println("消息发送成功！");
        }

        channel.close();
        collection.close();
    }
}
