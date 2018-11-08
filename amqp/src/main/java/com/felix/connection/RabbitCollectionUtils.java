package com.felix.connection;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitCollectionUtils {
    private RabbitCollectionUtils() {
    }

    public static final String FELIX_DURATION_TEST = "felix_duration_test";
    public static final String FELIX_NO_DURATION_TEST = "felix_no_duration_test";
    public static final String FELIX_SIMPLE_DEMO = "felix_simple_demo";
    public static final String FELIX_QUEUE_DEMO = "felix_queue_demo";
    public static final String FELIX_PUBLISH_SUBSCRIBE_DEMO = "felix_publish_subscribe_demo";
    public static final String FELIX_ROUTING_DEMO = "felix_routing_demo";
    public static final String FELIX_TOPIC_DEMO = "felix_topic_demo";

    public static Connection getCollection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setConnectionTimeout(10000);
        connectionFactory.setPassword("guest");
        connectionFactory.setUsername("guest");
        connectionFactory.setVirtualHost("/");
        return connectionFactory.newConnection(Address.parseAddresses("127.0.0.1:5672"));
    }

}
