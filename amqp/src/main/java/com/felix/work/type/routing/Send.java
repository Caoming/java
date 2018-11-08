package com.felix.work.type.routing;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * routing 模式适合，多消费者消费相同信息
 * @author felix
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection collection = RabbitCollectionUtils.getCollection();
        Channel channel = collection.createChannel();

        channel.exchangeDeclare(RabbitCollectionUtils.FELIX_ROUTING_DEMO,"direct",true);

        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息发送成功了");
            }

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {

            }
        });

        for(int i = 0; i < 100; i++) {
            channel.basicPublish(RabbitCollectionUtils.FELIX_ROUTING_DEMO,"info", MessageProperties.PERSISTENT_TEXT_PLAIN,("info message"+i).getBytes());
            channel.basicPublish(RabbitCollectionUtils.FELIX_ROUTING_DEMO,"warning", MessageProperties.PERSISTENT_TEXT_PLAIN,("warning message"+i).getBytes());
            channel.basicPublish(RabbitCollectionUtils.FELIX_ROUTING_DEMO,"warning", MessageProperties.PERSISTENT_TEXT_PLAIN,("error message"+i).getBytes());
            System.out.println("消息发送成功！");
        }

        channel.close();
        collection.close();
    }
}
