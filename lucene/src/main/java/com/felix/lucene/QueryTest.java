package com.felix.lucene;

import com.felix.model.GasInfo;
import org.apache.commons.lang3.time.StopWatch;

import java.text.Collator;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

/**
 * @Author caoming
 * @Date: 2020/9/14 12:36
 */
public class QueryTest {
    public static void main(String[] args) {
        TreeSet<GasInfo> infos = new TreeSet<>((o1, o2) -> Collator.getInstance(Locale.CHINESE).compare(o1.getGasName(),o2.getGasName()));
        List<GasInfo> listInfos = new ArrayList<>(20000);
        StopWatch started = StopWatch.createStarted();
        IntStream.range(1,20000).forEach(i ->{
            GasInfo gasInfo = new GasInfo();
            gasInfo.setGasCode(getRandomChinese()+getRandomChinese());
            gasInfo.setGasId(getRandomChinese()+getRandomChinese());
            gasInfo.setGasName(getRandomChinese()+getRandomChinese());
            if(i % 1000 == 0){
                gasInfo.setGasName("曹" + i);
            }
            infos.add(gasInfo);
            listInfos.add(gasInfo);
        });

        System.out.println("prepare data time is "+ String.valueOf(StopWatch.createStarted().getTime() - started.getTime()));
        AtomicLong totalTime = new AtomicLong(0L);
        IntStream.range(0,1000).forEach(i ->{
            long startTime = System.currentTimeMillis();
            List<GasInfo> gasInfos = new ArrayList<>();
            AtomicBoolean isMatch = new AtomicBoolean(false);
            AtomicInteger loopCount = new AtomicInteger();
            infos.stream().forEach(j ->{
                if(j.getGasName().contains(String.valueOf('曹'))){
                    gasInfos.add(j);
                    isMatch.set(true);
                }

                if(isMatch.get() && !j.getGasName().contains(String.valueOf(i))){
                    return;
                }
                loopCount.getAndIncrement();
            });
//            System.out.println("时间为：" +(System.currentTimeMillis() - startTime) + "数据为："+ gasInfos.size()+ "循环次数为："+ loopCount.get());
            totalTime.addAndGet(System.currentTimeMillis() - startTime);
        });
        System.out.println("花费时间为："+totalTime);
        totalTime.set(0L);
        IntStream.range(0,1000).forEach(i ->{
            long startTime = System.currentTimeMillis();
            List<GasInfo> gasInfos = new ArrayList<>();
            AtomicInteger loopCount = new AtomicInteger();
            listInfos.stream().forEach(j ->{
                if(j.getGasName().contains(String.valueOf("曹"))){
                    gasInfos.add(j);
                }
                loopCount.getAndIncrement();
            });
//            System.out.println("时间为：" +(System.currentTimeMillis() - startTime) + "数据为："+ gasInfos.size()+ "循环次数为："+ loopCount.get());
            totalTime.addAndGet(System.currentTimeMillis() - startTime);
        });

        System.out.println("花费时间为："+totalTime);
    }

    public static String getRandomChinese() {
        return new String(new char[] { (char) (new Random().nextInt(20902) + 19968) });
    }
}
