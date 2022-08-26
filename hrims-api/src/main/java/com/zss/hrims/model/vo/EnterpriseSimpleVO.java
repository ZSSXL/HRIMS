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
 * @date 2022/7/28 10:14
 * @desc 企业简明信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "企业简明信息")
public class EnterpriseSimpleVO implements Serializable {

    @ApiModelProperty(value = "企业Id")
    private String id;

    @ApiModelProperty(value = "企业名称")
    private String name;

    @ApiModelProperty(value = "企业地址")
    private String address;

    @ApiModelProperty(value = "负责人")
    private String principal;

    @ApiModelProperty(value = "联系电话")
    private String phone;

}
