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
 * @date 2022/7/26 16:52
 * @desc 企业信息 - 基本信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "企业基本信息")
public class EnterpriseBaseVO implements Serializable {

    @ApiModelProperty(value = "企业Id")
    private String id;

    @ApiModelProperty(value = "企业名称")
    private String name;
}
