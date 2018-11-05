package com.felix.confirm;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
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

        channel.exchangeDeclare(RabbitCollectionUtils.FELIX_DURATION_TEST,"direct",true);

        channel.queueDeclare(RabbitCollectionUtils.FELIX_DURATION_TEST,true,false,false,null);
        channel.queueBind(RabbitCollectionUtils.FELIX_DURATION_TEST,RabbitCollectionUtils.FELIX_DURATION_TEST,RabbitCollectionUtils.FELIX_DURATION_TEST,null);

        channel.confirmSelect();

        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("确认RabbitMQ成功收到");
            }

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("确认RabbitMQ没有收到");
            }
        });
        for(int i = 0; i < 1000; i++) {
            channel.basicPublish(RabbitCollectionUtils.FELIX_DURATION_TEST, RabbitCollectionUtils.FELIX_DURATION_TEST, null, ("hello, i am simple rabbitmq"+i).getBytes());
            System.out.println("消息发送成功！");
        }

        channel.close();
        collection.close();
    }
}
