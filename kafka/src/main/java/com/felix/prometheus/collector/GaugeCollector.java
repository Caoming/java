package com.felix.prometheus.collector;


import io.prometheus.client.Collector;
import io.prometheus.client.exporter.HTTPServer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author caoming
 * @Date: 2020/7/9 15:20
 */
@Component
public class GaugeCollector extends Collector {
    @Override
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> list = new ArrayList<>();
        String metricName = "radar";
        MetricFamilySamples.Sample sample = new MetricFamilySamples.Sample(metricName,
                Arrays.asList("newlink"), Arrays.asList("caoming"), 666);
        MetricFamilySamples.Sample sample2 = new MetricFamilySamples.Sample(metricName,Arrays.asList("yeji1","yeji2"),
                Arrays.asList("S","A"),888);
        MetricFamilySamples samples = new MetricFamilySamples(
                metricName, Type.GAUGE, "help", Arrays.asList(sample, sample2));

        list.add(samples);
        return list;
    }

    public static void main(String[] args) throws IOException {
        HTTPServer server = new HTTPServer(8686);
        new GaugeCollector().register();
    }
}
