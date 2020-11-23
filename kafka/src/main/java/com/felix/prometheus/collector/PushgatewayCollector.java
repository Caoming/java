package com.felix.prometheus.collector;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.PushGateway;
import org.springframework.stereotype.Component;

import io.prometheus.client.Collector;
import io.prometheus.client.exporter.HTTPServer;

/**
 * @Author caoming
 * @Date: 2020/7/9 15:20
 */
@Component
public class PushgatewayCollector extends Collector {
    @Override
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> list = new ArrayList<>();
        String metricName = "tuanyou1231";
        MetricFamilySamples.Sample sample = new MetricFamilySamples.Sample(metricName,
                Arrays.asList("newlink"), Arrays.asList("caoming"), 666);
        MetricFamilySamples.Sample sample2 = new MetricFamilySamples.Sample(metricName,Arrays.asList("yeji1","yeji2"),
                Arrays.asList("S","A"),888);
        MetricFamilySamples samples = new MetricFamilySamples(
                metricName, Type.GAUGE, "help", Arrays.asList(sample, sample2));

        list.add(samples);
        return list;
    }

    public static void main(String[] args) throws Exception {
        CollectorRegistry registry = CollectorRegistry.defaultRegistry;
        PushGateway pushGateway = new PushGateway("127.0.0.1:9091");
        while(true){
            pushGateway.push(new PushgatewayCollector(),"hello world");
            Thread.sleep(2000L);
        }
    }
}
