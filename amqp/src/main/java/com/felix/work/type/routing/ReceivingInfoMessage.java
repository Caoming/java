package com.felix.work.type.routing;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 持久化数据消费
 * @author felix
 */
public class ReceivingInfoMessage {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection collection = RabbitCollectionUtils.getCollection();
        final Channel channel = collection.createChannel();

        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,RabbitCollectionUtils.FELIX_ROUTING_DEMO,"info");

        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("接收到消息为：" + new String(body) + "deliveryTag为：" + deliveryTag);

            }
        };


        channel.basicConsume(queue,true, consumer);
    }

}
