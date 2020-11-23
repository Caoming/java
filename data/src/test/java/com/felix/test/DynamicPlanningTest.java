package com.felix.test;

import com.felix.data.vo.DynamicPlanningVo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import sun.applet.Main;

import java.util.List;
import java.util.Objects;

/**
 * 动态规划算法
 * @Author caoming
 * @Date: 2020/9/22 06:49
 */
public class DynamicPlanningTest {
    List<DynamicPlanningVo> vos = Lists.newArrayList();
    @Before
    public void prepareData(){
        vos.add(new DynamicPlanningVo(5,1,4,1));
        vos.add(new DynamicPlanningVo(1,3,5,2));
        vos.add(new DynamicPlanningVo(8,0,6,3));
        vos.add(new DynamicPlanningVo(4,4,7,4));
        vos.add(new DynamicPlanningVo(6,3,8,5));
        vos.add(new DynamicPlanningVo(3,5,9,6));
        vos.add(new DynamicPlanningVo(2,6,10,7));
        vos.add(new DynamicPlanningVo(4,8,11,8));
    }

    @Test
    public void testDynamicPlanning(){
        Integer lastValue1 = maxValue(vos.get(0).getValue(), 0,vos.get(0), vos);
        Integer lastValue2 = maxValue(vos.get(1).getValue(), 1,vos.get(1), vos);

        System.out.println(Math.max(lastValue1,lastValue2));
        System.out.println(lastValue1);
    }


    public Integer maxValue(Integer value, Integer index,
                            DynamicPlanningVo vo,  List<DynamicPlanningVo> vos){
        if(index >= vos.size() - 1){
            return value;
        }
        DynamicPlanningVo dynamicPlanningVo = vos.get(index + 1);
        if(dynamicPlanningVo.getStart() >= vo.getEnd()){
            int value3 = value + dynamicPlanningVo.getValue();
            Integer value1 = maxValue(value3, index + 1, dynamicPlanningVo, vos);
            Integer value2 = maxValue(value, index + 1, vo, vos);
            return Math.max(value1, value2);
        }else{
            return maxValue(value, index + 1, vo, vos);
        }
    }
}
