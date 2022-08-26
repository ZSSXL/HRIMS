package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/25 13:51
 * @desc 简历来源
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "简历来源")
public class ResumeSourceDTO implements Serializable {

    @ApiModelProperty(value = "简历来源")
    private String source;

    @ApiModelProperty(value = "来源别名")
    private String alias;
}
