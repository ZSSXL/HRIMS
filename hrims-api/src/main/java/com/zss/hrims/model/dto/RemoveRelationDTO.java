package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/8/15 16:39
 * @desc 关系移除
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "关系移除")
public class RemoveRelationDTO implements Serializable {

    @ApiModelProperty(value = "one")
    private String key;

    @ApiModelProperty(value = "many")
    private String value;
}
