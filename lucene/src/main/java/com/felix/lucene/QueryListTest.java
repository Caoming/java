package com.felix.lucene;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;

import com.felix.model.GasInfo;

/**
 * @Author caoming
 * @Date: 2020/9/14 12:36
 */
public class QueryListTest {
    public static void main(String[] args) {
        List<GasInfo> infos = new ArrayList<>();

        StopWatch started = StopWatch.createStarted();
        IntStream.range(1,20000).forEach(i ->{
            GasInfo gasInfo = new GasInfo();
            gasInfo.setGasCode(String.valueOf(RandomUtils.nextLong(0L,10000000000L)));
            gasInfo.setGasId(String.valueOf(RandomUtils.nextLong(0L,10000000000L)));
            gasInfo.setGasName(String.valueOf(RandomUtils.nextLong(0L,10000000000L)));
            infos.add(gasInfo);
        });

        System.out.println("prepare data time is "+ String.valueOf(StopWatch.createStarted().getTime() - started.getTime()));
        IntStream.range(0,100).forEach(i ->{
            long startTime = System.currentTimeMillis();
            List<GasInfo> gasInfos = new ArrayList<>();
            AtomicInteger loopCount = new AtomicInteger();
            infos.stream().forEach(j ->{
                if(j.getGasName().contains(String.valueOf(i))){
                    gasInfos.add(j);
                }
                loopCount.getAndIncrement();

            });
            System.out.println("时间为：" +(System.currentTimeMillis() - startTime) + "数据为："+ gasInfos.size()+ "循环次数为："+ loopCount.get());
        });
    }
}
