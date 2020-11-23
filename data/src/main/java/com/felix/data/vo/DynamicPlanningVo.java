package com.felix.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 动态规划的对象
 * @Author caoming
 * @Date: 2020/9/22 06:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DynamicPlanningVo implements Serializable {
    /**
     * 任务所属价值
     */
    private Integer value;

    /**
     * 任务时间开始点
     */
    private Integer start;

    /**
     * 任务结束点
     */
    private Integer end;

    /**
     * 任务编号
     */
    private Integer taskNum;

}
