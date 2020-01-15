package com.felix.work.type.routing;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * routing 模式适合，多消费者消费相同信息
 * @author felix
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection collection = RabbitCollectionUtils.getCollection();
        Channel channel = collection.createChannel();

        Map<String, Object> arg = new HashMap<>();
        arg.put("x-max-priority",10);
        channel.exchangeDeclare(RabbitCollectionUtils.FELIX_ROUTING_DEMO,"direct",
                true);
        channel.queueDeclare("caoming01",true,false,false,arg);
        channel.queueBind("caoming01", RabbitCollectionUtils.FELIX_ROUTING_DEMO, "info");

        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息发送成功了");
            }

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {

            }
        });

        for(int i = 0; i < 100; i++) {
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            if(i%2 ==0) {
                builder.priority(10);
                channel.basicPublish(RabbitCollectionUtils.FELIX_ROUTING_DEMO, "info", builder.build(), ("info message" + i).getBytes());
            }else {
                builder.priority(0);
                channel.basicPublish(RabbitCollectionUtils.FELIX_ROUTING_DEMO, "info", builder.build(), ("info message" + i).getBytes());
            }
//            channel.basicPublish(RabbitCollectionUtils.FELIX_ROUTING_DEMO,"warning", MessageProperties.PERSISTENT_TEXT_PLAIN,("error message"+i).getBytes());
            System.out.println("消息发送成功！");
        }

        channel.close();
        collection.close();
    }
}
