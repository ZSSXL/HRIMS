package com.zss.hrims.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ZSS
 * @date 2022/8/1 16:27
 * @desc 企业详细信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "企业详细信息")
public class EnterpriseDetailVO implements Serializable {

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

    @ApiModelProperty(value = "岗位统计")
    private Map<String, Map<String, Integer>> postCount;
}
