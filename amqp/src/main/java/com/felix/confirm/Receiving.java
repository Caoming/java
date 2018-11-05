package com.felix.confirm;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 持久化数据消费
 * @author felix
 */
public class Receiving {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection collection = RabbitCollectionUtils.getCollection();
        final Channel channel = collection.createChannel();

        channel.basicQos(100);
        Consumer consumer = new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("接收到消息为：" + new String(body) + "consumerTag为：" + consumerTag);

                channel.basicAck(deliveryTag,true);
            }

        };


        channel.basicConsume(RabbitCollectionUtils.FELIX_DURATION_TEST, consumer);
    }

}
