package com.felix.work.type.rpc;

import com.felix.connection.RabbitCollectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Server {

    public static void main(String[] args)  throws IOException, TimeoutException, InterruptedException {
        Connection collection = RabbitCollectionUtils.getCollection();
        final Channel channel = collection.createChannel();

        channel.queueDeclare("rpc_felix", false, false, false, null);
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(properties.getCorrelationId())
                        .build();

                String response = "";

                try {
                    String message = new String(body, "UTF-8");
                    int n = Integer.parseInt(message);

                    System.out.println(" [.] fib(" + message + ")");
                    response += 1;
                } catch (RuntimeException e) {
                    System.out.println(" [.] " + e.toString());
                } finally {
                    channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    // RabbitMq consumer worker thread notifies the RPC server owner thread
                    synchronized (this) {
                        this.notify();
                    }
                }
            }
        };

        channel.basicConsume("rpc_felix", false, consumer);
    }
}
