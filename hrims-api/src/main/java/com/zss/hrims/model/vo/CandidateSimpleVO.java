package com.zss.hrims.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/29 17:11
 * @desc 候选人简略信息
 */
@Data
@ApiModel(value = "候选人简略信息")
public class CandidateSimpleVO implements Serializable {

    @ApiModelProperty(value = "候选人Id")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "岗位")
    private String post;

    @ApiModelProperty(value = "毕业时间")
    private String graduationTime;

    @ApiModelProperty(value = "企业")
    private String enterprise;

    @ApiModelProperty(value = "评级")
    private String rating;
}
