package com.zss.hrims.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/26 17:04
 * @desc 面试信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "面试信息")
public class InterviewVO implements Serializable {

    @ApiModelProperty(value = "面试Id")
    private String id;

    @ApiModelProperty(value = "候选人Id")
    private String candidateId;

    @ApiModelProperty(value = "面试轮次")
    private String rounds;

    @ApiModelProperty(value = "面试时间")
    private String interviewTime;

    @ApiModelProperty(value = "状态 -- 等待面试/面试中/成功/失败")
    private String state;

    @ApiModelProperty(value = "人事面试意见")
    private String hrInterviewAdvice;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
