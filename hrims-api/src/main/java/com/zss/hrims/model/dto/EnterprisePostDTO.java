package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/25 22:58
 * @desc 企业岗位实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "企业岗位实体")
public class EnterprisePostDTO implements Serializable {

    @ApiModelProperty(value = "企业Id")
    private String enterpriseId;

    @ApiModelProperty(value = "岗位Id")
    private String postId;

    @ApiModelProperty(value = "企业对该岗位的要求")
    private String demand;

    @ApiModelProperty(value = "年限要求")
    private String ageLimit;
}
