package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/27 13:23
 * @desc 更新面试信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "更新面试信息")
public class UpdateInterviewDTO implements Serializable {

    @ApiModelProperty(value = "面试Id")
    private String id;

    @ApiModelProperty(value = "面试时间")
    private String interviewTime;

    @ApiModelProperty(value = "状态 -- 等待面试/面试中/成功/失败")
    private String state;

    @ApiModelProperty(value = "人事面试意见")
    private String hrInterviewAdvice;
}
