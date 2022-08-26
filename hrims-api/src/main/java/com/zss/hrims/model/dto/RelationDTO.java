package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/25 13:15
 * @desc 关系创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "关系创建")
public class RelationDTO implements Serializable {

    @ApiModelProperty(value = "one")
    private String key;

    @ApiModelProperty(value = "many")
    private List<String> values;
}
