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
 * @date 2022/7/23 22:35
 * @desc 企业信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "企业信息")
public class EnterpriseVO implements Serializable {

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

    @ApiModelProperty(value = "是否在合作")
    private Boolean cooperation;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
