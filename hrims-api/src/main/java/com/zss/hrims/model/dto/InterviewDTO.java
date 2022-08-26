package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/26 9:54
 * @desc 添加面试
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "添加面试")
public class InterviewDTO implements Serializable {

    @ApiModelProperty(value = "候选人")
    private String candidateId;

    @ApiModelProperty(value = "面试时间")
    private String interviewTime;

    @ApiModelProperty(value = "状态 -- 为空则是等待面试")
    private String state;
}
