package com.felix.work.type.queue;

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

        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("接收到消息为：" + new String(body) + "deliveryTag为：" + deliveryTag);

//                try{
//                    channel.basicAck(deliveryTag,false);
//                }catch (Exception e){
//                    e.printStackTrace();
//                    channel.basicNack(deliveryTag,false,true);
//                }
            }
        };


        channel.basicConsume(RabbitCollectionUtils.FELIX_QUEUE_DEMO,true, consumer);
    }

}
