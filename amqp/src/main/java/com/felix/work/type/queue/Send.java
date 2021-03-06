package com.felix.work.type.queue;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.*;

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

        channel.queueDeclare(RabbitCollectionUtils.FELIX_QUEUE_DEMO,true,false,false,null);

        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息发送成功了");
            }

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {

            }
        });

        for(int i = 0; i < 100; i++) {
            channel.basicPublish("",RabbitCollectionUtils.FELIX_QUEUE_DEMO, MessageProperties.PERSISTENT_TEXT_PLAIN,("hello world"+i).getBytes());
            System.out.println("消息发送成功！");
        }

        channel.close();
        collection.close();
    }
}
