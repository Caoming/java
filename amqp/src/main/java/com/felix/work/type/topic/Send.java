package com.felix.work.type.topic;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * topic模式，适合做一些多消费端且消费端接收数据不一样的场景
 * @author felix
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection collection = RabbitCollectionUtils.getCollection();
        Channel channel = collection.createChannel();

        channel.exchangeDeclare(RabbitCollectionUtils.FELIX_TOPIC_DEMO,"topic",true);

        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息发送成功了");
            }

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {

            }
        });

        for(int i = 0; i < 100; i++) {
            channel.basicPublish(RabbitCollectionUtils.FELIX_TOPIC_DEMO,"caoming.felix.info", MessageProperties.PERSISTENT_TEXT_PLAIN,("info message"+i).getBytes());
            channel.basicPublish(RabbitCollectionUtils.FELIX_TOPIC_DEMO,"felix.warning", MessageProperties.PERSISTENT_TEXT_PLAIN,("warning message"+i).getBytes());
            channel.basicPublish(RabbitCollectionUtils.FELIX_TOPIC_DEMO,"felix.error.warning", MessageProperties.PERSISTENT_TEXT_PLAIN,("error FELIX message"+i).getBytes());
        }

        channel.close();
        collection.close();
    }
}
