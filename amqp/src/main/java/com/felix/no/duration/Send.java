package com.felix.no.duration;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 持久化数据处理
 * @author felix
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection collection = RabbitCollectionUtils.getCollection();
        Channel channel = collection.createChannel();

        channel.queueDeclare(RabbitCollectionUtils.FELIX_NO_DURATION_TEST,false,false,false,null);
        channel.exchangeDeclare(RabbitCollectionUtils.FELIX_NO_DURATION_TEST,"direct",false);

        channel.exchangeBind(RabbitCollectionUtils.FELIX_NO_DURATION_TEST,RabbitCollectionUtils.FELIX_NO_DURATION_TEST,RabbitCollectionUtils.FELIX_NO_DURATION_TEST);

        for(int i = 0; i < 1000; i++) {
            channel.basicPublish("", RabbitCollectionUtils.FELIX_NO_DURATION_TEST, null, ("hello, i am simple rabbitmq"+i).getBytes());
            System.out.println("消息发送成功！");
        }

        channel.close();
        collection.close();
    }
}
