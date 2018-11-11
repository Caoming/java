package com.felix.work.type.rpc;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * RPC的客户端
 */
public class Client {
    private Connection connection = null;
    private Channel channel = null;

    public Client() throws IOException {
        connection = RabbitCollectionUtils.getCollection();
        channel= connection.createChannel();
    }

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Client client = new Client();

        for(int i = 0; i < 32; i++){
            client.call(i+"rpc");
        }
    }

    private  void call(String string) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();


        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

        final String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().
                correlationId(corrId).replyTo(replyQueueName).build();

        channel.basicPublish("","rpc_felix",properties,string.getBytes());

        String cTag = channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(corrId)) {
                    response.offer(new String(body));
                    long deliveryTag = envelope.getDeliveryTag();
                    System.out.println("接收到消息为：" + new String(body) + "deliveryTag为：" + deliveryTag);
                }

            }
        });

        String result = response.take();
        channel.basicCancel(cTag);
        System.out.println(result);
    }
}
