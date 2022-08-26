package com.zss.hrims.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/23 22:52
 * @desc 面试实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "hri_interview")
public class Interview extends Model<Interview> implements Serializable {

    /**
     * 面试Id
     */
    private String id;

    /**
     * 候选人Id
     */
    private String candidateId;

    /**
     * 面试轮次
     */
    private Integer rounds;

    /**
     * 面试时间
     */
    private String interviewTime;

    /**
     * 状态 -- 等待面试/面试中/成功/失败
     */
    private Integer state;

    /**
     * 人事面试意见
     */
    private String hrInterviewAdvice;

    /**
     * 创建时间
     */
    private String createTime;
}
